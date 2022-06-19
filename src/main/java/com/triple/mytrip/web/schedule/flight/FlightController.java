package com.triple.mytrip.web.schedule.flight;

import com.triple.mytrip.domain.schedule.flight.Flight;
import com.triple.mytrip.domain.schedule.flight.FlightService;
import com.triple.mytrip.web.common.Result;
import com.triple.mytrip.web.schedule.flight.request.FlightSaveRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.triple.mytrip.web.schedule.flight.FlightConverter.saveRequestToEntity;

@RestController
@RequiredArgsConstructor
public class FlightController {



}
