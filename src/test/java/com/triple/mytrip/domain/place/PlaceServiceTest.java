package com.triple.mytrip.domain.place;

import com.triple.mytrip.domain.common.TripCategory;
import com.triple.mytrip.domain.member.Member;
import com.triple.mytrip.domain.place.accommodation.Accommodation;
import com.triple.mytrip.domain.place.flight.Flight;
import com.triple.mytrip.domain.place.restaurant.Restaurant;
import com.triple.mytrip.domain.place.shop.Shop;
import com.triple.mytrip.domain.place.tour.Tour;
import com.triple.mytrip.domain.trip.Trip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class PlaceServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    PlaceService placeService;

    @Test
    @Rollback(value = false)
    public void 장소_등록() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place tour = new Tour("투어", LocalDate.now(), "위치", 0);
        Place shop = new Shop("쇼핑", LocalDate.now(), "위치", 0);
        Place restaurant = new Restaurant("음식점", LocalDate.now(), "위치", 0);
        Place accommodation = new Accommodation("숙박", LocalDate.now(), "위치", 0);
        Place flight = new Flight("항공", LocalDate.now(), "위치", 0, "222", "대한항공",
                LocalDate.now(), LocalTime.now(), LocalTime.now(), "인천공항", "제주공항");

        // when

        Long tourId = placeService.save(trip.getId(), tour);
        Long shopId = placeService.save(trip.getId(), shop);
        Long restaurantId = placeService.save(trip.getId(), restaurant);
        Long accommodationId = placeService.save(trip.getId(), accommodation);
        Long flightId = placeService.save(trip.getId(), flight);

        em.flush();
        em.clear();

        Place findTour = placeService.getOne(tourId);
        Place findShop = placeService.getOne(shopId);
        Place findRestaurant = placeService.getOne(restaurantId);
        Place findAccommodation = placeService.getOne(accommodationId);
        Place findFlight = placeService.getOne(flightId);

        // then

        assertThat(findTour.getId()).isEqualTo(tourId);
        assertThat(findShop.getId()).isEqualTo(shopId);
        assertThat(findRestaurant.getId()).isEqualTo(restaurantId);
        assertThat(findAccommodation.getId()).isEqualTo(accommodationId);
        assertThat(findFlight.getId()).isEqualTo(flightId);
    }

    @Test
    public void 장소_수정() throws Exception {
        // given
        Member member = createMember("email1", "1234");
        Trip trip = createTrip(member, "제주");
        Place place = createPlace(trip, "장소1", LocalDate.now(), "위치1", 0);

        // when
        placeService.editDate(place.getId(), LocalDate.of(2022, 5, 20));
        placeService.editMemo(place.getId(), "메모");
        placeService.editOrder(place.getId(), 1);
        placeService.editVisitTime(place.getId(), LocalTime.of(1, 10, 10));

        em.flush();
        em.clear();

        Place modified = em.find(Place.class, place.getId());
        // then
        assertThat(modified.getDate()).isEqualTo(LocalDate.of(2022, 5, 20));
        assertThat(modified.getMemo()).isEqualTo("메모");
        assertThat(modified.getPlaceOrder()).isEqualTo(1);
        assertThat(modified.getVisitTime()).isEqualTo(LocalTime.of(1, 10, 10));

    }

    private Place createPlace(Trip trip, String name, LocalDate date, String position, int placeOrder) {
        Place place = new Place(name, date, position, placeOrder);
        place.addTrip(trip);
        em.persist(place);
        return place;
    }

    private Trip createTrip(Member member, String city) {
        Trip trip = new Trip(city);
        trip.addMember(member);
        em.persist(trip);
        return trip;
    }

    private Member createMember(String email, String password) {
        Member member = new Member(email, password);
        em.persist(member);
        return member;
    }
}