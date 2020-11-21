package com.hackerrank.weather.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherApiRestController {
	 @Autowired
	    private WeatherService weatherService;

	    @Autowired
	    private LocationService locationService;

	    @Autowired
	    private Validator validator;



	    @RequestMapping("/returnAllWeatherData")
	    public List<Weather> getAllWeatherDate() throws EntityNotFoundException{
	        List<Weather> weatherList=weatherService.getAllWeatherData();
	       

	        


	        return weatherList.;

	    }

	    @RequestMapping("/getWeather/{id}")
	    public Weather Weather(@PathVariable int id) {

	        Weather Weather=WeatherService.getWeather(id);
	        try {
	            WeatherItems=WeatherItemProxy.getItemsByWeatherId(Weather.getWeatherId());
	        }catch(Exception e) {
	            throw new EntityNotFoundException("No Item Found for test Weather Id test update from STS:"+WeatherId);
	        }

	        Weather.setWeatherItems(WeatherItems);
	        return Weather;

	    }


	    @RequestMapping(method=RequestMethod.POST,value="/createWeather/")
	    public ResponseEntity<String> addWeather(@RequestBody Weather Weather) {
	        Set<ConstraintViolation<Weather>> violations =validator.validate(Weather);
	        StringBuilder sb=new StringBuilder();
	        if(violations.size()>0) {
	            for (ConstraintViolation<Weather> violation : violations) {
	                sb.append((violation.getMessage()));

	            }
	            throw new EntityNotFoundException("Input Validation Failed   tsample test:\n"+sb.toString());
	        }

	        WeatherService.createWeather(Weather);

	        return ResponseEntity.ok("Weather Added successfully");

	    }


	    @RequestMapping(method=RequestMethod.PUT,value="/updateWeather/")
	    public ResponseEntity<String> updateWeather(@RequestBody Weather Weather) {
	        Set<ConstraintViolation<Weather>> violations =validator.validate(Weather);
	        StringBuilder sb=new StringBuilder();
	        if(violations.size()>0) {
	            for (ConstraintViolation<Weather> violation : violations) {
	                sb.append((violation.getMessage()));

	            }
	            throw new EntityNotFoundException("Input Validation Failed :"+sb.toString());
	        }
	        WeatherService.updateWeather(Weather);

	        return ResponseEntity.ok("Weather Updated successfully");

	    }


	    @RequestMapping(method=RequestMethod.DELETE,value="/deleteWeather/{WeatherId}")
	    public void deleteWeather(@PathVariable Long WeatherId) {

	        weatherService.deleteWeather(WeatherId);

	    }
}
