package com.triple.mytrip.domain.trip;

import com.triple.mytrip.domain.budget.Budget;
import com.triple.mytrip.domain.budget.BudgetCategory;
import com.triple.mytrip.domain.budget.PaymentPlan;
import com.triple.mytrip.domain.checklistcategory.ChecklistCategory;
import com.triple.mytrip.domain.checklistcategory.checklist.Checklist;
import com.triple.mytrip.domain.exception.EntityNotWithinPeriodException;
import com.triple.mytrip.domain.flight.Flight;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.place.Place;
import com.triple.mytrip.domain.place.PlaceType;
import com.triple.mytrip.domain.schedule.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class TripServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    TripService tripService;

    @Test
    public void 가계부_저장() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = Trip.builder()
                .city("제주")
                .arrivalDate(LocalDate.of(2022, 10, 10))
                .departureDate(LocalDate.of(2022, 10, 05))
                .title("제주 여행")
                .build();
        em.persist(trip);

        Budget budget1 = Budget.builder()
                .budgetCategory(BudgetCategory.ACCOMMODATIONS)
                .price(10000)
                .date(LocalDate.of(2022, 10, 10))
                .paymentPlan(PaymentPlan.CARD)
                .order(0)
                .place("숙소1")
                .content("content1")
                .build();

        Budget budget2 = Budget.builder()
                .budgetCategory(BudgetCategory.ACCOMMODATIONS)
                .price(20000)
                .date(LocalDate.of(2022, 11, 11))
                .paymentPlan(PaymentPlan.CARD)
                .order(0)
                .place("숙소2")
                .content("content2")
                .build();
        // when
        Long savedId = tripService.addBudget(trip.getId(), budget1);

        // then
        assertThat(em.find(Budget.class, savedId)).isEqualTo(budget1);
        assertThatThrownBy(() -> tripService.addBudget(trip.getId(), budget2))
                .isInstanceOf(EntityNotWithinPeriodException.class);
    }

    @Test
    public void 카테고리_저장() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category = ChecklistCategory.builder().name("카테고리1").basicOfferStatus(false).build();

        // when
        Long savedId = tripService.addChecklistCategory(trip.getId(), category);

        // then
        assertThat(em.find(ChecklistCategory.class, savedId)).isEqualTo(category);
    }
    
    @Test
    public void 항공일정_저장() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Flight flight = Flight.builder()
                .flightNumber("number1")
                .airline("대한항공")
                .departureDate(LocalDate.now().plusDays(1))
                .departureTime(LocalTime.of(10, 10))
                .arrivalTime(LocalTime.of(12, 10))
                .departureAirport("제주공항")
                .arrivalAirport("인천공항")
                .build();

        // when
        Map<String, Long> idMap = tripService.addFlightSchedule(trip.getId(), flight);

        // then
        Long flightId = idMap.get("flightId");
        Long scheduleId = idMap.get("scheduleId");
        assertThat(em.find(Schedule.class, scheduleId).getFlight()).isEqualTo(flight);
        assertThat(em.find(Flight.class, flightId)).isEqualTo(flight);

    }

    @Test
    public void 장소일정_저장() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place = createPlace("장소1", "위치1", PlaceType.RESTAURANT);
        Schedule schedule = Schedule.builder()
                .date(LocalDate.now().plusDays(1))
                .visitOrder(1)
                .arrangeOrder(1)
                .build();

        // when
        Long savedId = tripService.addPlaceSchedule(trip.getId(), place.getId(), schedule);

        // then
        Schedule findSchedule = em.find(Schedule.class, savedId);
        assertThat(findSchedule).isEqualTo(schedule);
        assertThat(findSchedule.getPlace()).isEqualTo(place);
    }

    @Test
    public void 여행_조회_단건() throws Exception {
        // given
        Member member = createMember("email1", "1234");

        // when
        Trip trip = createTrip(member, "제주");
        Trip findTrip = tripService.getOne(trip.getId());

        // then
        assertThat(trip).isEqualTo(findTrip);
        assertThat(findTrip.getTitle()).isEqualTo("제주 여행");
    }

    @Test
    public void 여행_조회_일정() throws Exception {
        //given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place1 = createPlace("장소1", "위치1", PlaceType.SHOP);
        Schedule schedule1 = createSchedule(trip, place1, LocalDate.now().plusDays(1), 1, 2);
        Schedule schedule2 = createSchedule(trip, place1, LocalDate.now().plusDays(2), 2, 1);
        Flight flight = createFlight(trip, "number1", "대한항공",
                LocalDate.now().plusDays(1), LocalTime.of(10, 10),
                LocalTime.of(12, 10), "인천공항", "제주공항");
        Schedule schedule3 = createFlightSchedule(flight, trip);

        // when
        em.flush();
        em.clear();
        Trip findTrip = tripService.getOneWithSchedule(trip.getId());

        // then
        List<Schedule> schedules = findTrip.getSchedules();
        assertThat(schedules.size()).isEqualTo(3);
        assertThat(schedules.get(0).getFlight().getFlightNumber()).isEqualTo("number1");
        assertThat(schedules.get(1).getPlace().getName()).isEqualTo("장소1");
        assertThat(schedules.get(2).getPlace().getName()).isEqualTo("장소1");
    }

    @Test
    public void 여행_조회_가계부() throws Exception {
        //given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Budget budget1 = createBudget(trip, 10000, "숙소");
        Budget budget2 = createBudget(trip, 20000, "음식점");

        // when
        em.flush();
        em.clear();
        Trip findTrip = tripService.getOneWithBudget(trip.getId());

        // then
        List<Budget> budgets = findTrip.getBudgets();
        assertThat(budgets.size()).isEqualTo(2);
    }

    @Test
    public void 여행_조회_체크리스트() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        ChecklistCategory category1 = createCategory(trip, "카테고리1");
        ChecklistCategory category2 = createCategory(trip, "카테고리2");
        Checklist checklist1 = createChecklist(category1, "체크리스트1");
        Checklist checklist2 = createChecklist(category1, "체크리스트2");
        em.flush();
        em.clear();

        // when
        Trip findTrip = tripService.getOneWithChecklistCategory(trip.getId());

        // then
        List<ChecklistCategory> checklistCategories = trip.getChecklistCategories();
        for (ChecklistCategory checklistCategory : checklistCategories) {
            System.out.println("checklistCategory.getName() = " + checklistCategory.getName());
        }
        assertThat(checklistCategories.size()).isEqualTo(2);
        assertThat(checklistCategories.get(0).getChecklists().size()).isEqualTo(2);
        assertThat(checklistCategories.get(1).getChecklists().size()).isEqualTo(0);
    }

    @Test
    public void 여행_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");

        em.flush();
        em.clear();

        // when
        Trip modified = new Trip("제주", "제목수정", LocalDate.of(2022, 02, 02),
                LocalDate.of(2022, 02, 01), Partner.CHILD, null);

        tripService.modify(trip.getId(), modified);
        em.flush();
        em.clear();

        modified = em.find(Trip.class, trip.getId());
        // then
        assertThat(modified.getCity()).isEqualTo("제주");
        assertThat(modified.getTitle()).isEqualTo("제목수정");
        assertThat(modified.getPeriod().getArrivalDate()).isEqualTo(LocalDate.of(2022, 02, 02));
        assertThat(modified.getPeriod().getDepartureDate()).isEqualTo(LocalDate.of(2022, 02, 01));
        assertThat(modified.getPartner()).isEqualTo(Partner.CHILD);
        assertThat(modified.getTripStyle()).isEqualTo(null);
    }

    @Test
    public void 여행_삭제() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");

        em.flush();
        em.clear();
        // when
        tripService.remove(trip.getId());
        em.flush();
        em.clear();
        Trip deletedTrip = em.find(Trip.class, trip.getId());

        // then
        assertThat(deletedTrip).isNull();
    }

    private Flight createFlight(Trip trip, String flightNumber, String airline, LocalDate departureDate, LocalTime departureTime, LocalTime arrivalTime, String departureAirport, String arrivalAirport) {
        Flight flight = Flight.builder()
                .flightNumber(flightNumber)
                .airline(airline)
                .departureDate(departureDate)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .build();
        em.persist(flight);
        return flight;
    }

    private Schedule createFlightSchedule(Flight flight, Trip trip) {
        Schedule schedule = Schedule.builder()
                .date(flight.getDepartureDate())
                .visitOrder(0)
                .arrangeOrder(0)
                .build();
        schedule.addTrip(trip);
        schedule.addFlight(flight);
        em.persist(schedule);
        return schedule;
    }

    private Schedule createSchedule(Trip trip, Place place, LocalDate date, Integer visitOrder, Integer arrangeOrder) {
        Schedule schedule = Schedule.builder()
                .date(date)
                .visitOrder(visitOrder)
                .arrangeOrder(arrangeOrder)
                .build();

        schedule.addPlace(place);
        schedule.addTrip(trip);
        em.persist(schedule);
        return schedule;
    }

    private Budget createBudget(Trip trip, int price, String place) {
        Budget budget = Budget.builder()
                .budgetCategory(BudgetCategory.ACCOMMODATIONS)
                .price(price)
                .date(LocalDate.now().plusDays(1))
                .paymentPlan(PaymentPlan.CARD)
                .order(0)
                .place(place)
                .content("content1")
                .build();
        budget.addTrip(trip);
        em.persist(budget);
        return budget;
    }

    private Checklist createChecklist(ChecklistCategory checklistCategory, String name) {
        Checklist checklist = Checklist.builder()
                .name(name)
                .basicOfferStatus(false)
                .checkStatus(false)
                .build();
        checklist.addCategory(checklistCategory);
        em.persist(checklist);
        return checklist;
    }

    private Place createPlace(String name, String location, PlaceType placeType) {
        Place place = Place.builder()
                .name(name)
                .location(location)
                .placeType(placeType)
                .build();
        em.persist(place);
        return place;
    }

    private Trip createTrip(Member member, String city) {
        Trip trip = Trip.builder().city(city).title(city + " 여행")
                .departureDate(LocalDate.now())
                .arrivalDate(LocalDate.now().plusDays(3)).build();
        trip.addMember(member);
        em.persist(trip);
        return trip;
    }

    private Member createMember(String email, String password) {
        Member member = new Member(email, password);
        em.persist(member);
        return member;
    }

    private ChecklistCategory createCategory(Trip trip, String name) {
        ChecklistCategory category = ChecklistCategory.builder().name(name).basicOfferStatus(false).build();
        category.addTrip(trip);
        em.persist(category);
        return category;
    }

}