package ca.sheridancolege.kayamu.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancolege.kayamu.beans.Appointment;
import ca.sheridancolege.kayamu.repositories.AppointmentRepository;

@Controller
public class HomeController {

	@Autowired
	private AppointmentRepository appointmentRepository;

	private Integer curretPage = 0;

    //for getting appointmentList 
	private Page<Appointment> appointmentList = null;
	
	//showing record per page
	private final int recordPerPage = 5;

	//for creating paging 
	private Pageable paging = PageRequest.of(0, recordPerPage);

	//for creating first sort order request
	private Sort sort = Sort.by("id").ascending();

	//for creating order type
	private List<Sort> sortList = new ArrayList<Sort>();
	
    //for getting filters as a text after that show in the HTML
	private String newSearch = "";

	//for creating Db filter
	private Example<Appointment> example;
	

	@GetMapping(value = { "/", "/{id}" })
	public String index(Model model, @PathVariable(required = false) String id) {

		
		//if id PathVariable is null
		if (id == null) {
			example = null; // for reset filter

			if (paging == null) {
				paging = PageRequest.of(0, recordPerPage); // for set to first page
			}
			System.out.println("pageNumber null");
			

		//if id PathVariable is not null	
		} else if (Integer.parseInt(id) >= 0) {
			try {
				curretPage = Integer.valueOf(id) - 1;
				paging = PageRequest.of(curretPage, recordPerPage, sort);

			} catch (Exception e) {
				curretPage = 0;
				paging = PageRequest.of(0, recordPerPage);
				System.out.println("pageNumber error");
				
				System.out.println("Current Page Exception");
			}
		}

		Integer totalRecord;

		//if filter exist
		if (example != null) {
			//get only filtered records
			this.appointmentList = appointmentRepository.findAll(example, paging);
			//get only filtered records count
			totalRecord = appointmentRepository.findAll(example).size();

		} 
		
		//if no filter
		else {
			//get all records with paging
			this.appointmentList = appointmentRepository.findAll(paging);
			//get all records count
			totalRecord = appointmentRepository.findAll().size(); 

		}

		
		
		//for showing pages in the html 1...n
		List<String> pageList = new ArrayList<>();

		System.out.println("Total " + totalRecord);
		int pageCount = (int) Math.ceil(((float) totalRecord / recordPerPage));

		for (Integer i = 1; i <= pageCount; i++) {
			pageList.add(i.toString());
		}
		
		model.addAttribute("pageList", pageList);

		
		
		model.addAttribute("appointment", new Appointment());
		model.addAttribute("totalRecord", totalRecord.toString() + " record found in");

		model.addAttribute("appointmentListBefore", appointmentList);
		model.addAttribute("appointmentListAfter", "");
		
		model.addAttribute("appointmentEdit", new Appointment());
		
		try {
			model.addAttribute("sortText", "Order: " + sort.toString());
		} catch (Exception e) {
			model.addAttribute("sortText", "Order: ");
		}
		try {
			model.addAttribute("searchText", "Filter: " + newSearch);
		} catch (Exception e) {
			model.addAttribute("searchText", "Filter: ");
		}

		return "index";
	}

	@PostMapping("/getAppointment/sendInfo")
	public String updateInfo(Model model, @ModelAttribute Appointment appointmentEdit) {

		Appointment newApp = appointmentRepository.findById(appointmentEdit.getId()).get();

		newApp.setPurpose(appointmentEdit.getPurpose());
		newApp.setStartDate(appointmentEdit.getStartDate());
		newApp.setStartTime(appointmentEdit.getStartTime());
		newApp.setPerson(appointmentEdit.getPerson());

		appointmentRepository.save(newApp);

		
		System.out.println("Save Current Page " + curretPage.toString());
		
		// / makes filter null Thus I m calling 1st page it will be 0 in the method
		
		return "redirect:/"+curretPage.toString();
		

	}

	@PostMapping("/sendInfo")
	public String sendInfo(Model model, @ModelAttribute Appointment appointment) {

		appointmentRepository.save(appointment);
		return "redirect:/"+curretPage.toString();
	}

	@GetMapping("/getAppointment/{id}")
	public String findById(Model model, @PathVariable String id) {

		paging = PageRequest.of(curretPage, recordPerPage);

		//List<Appointment> appointmentAll;

		if (example != null) {
			appointmentList = appointmentRepository.findAll(example, paging);
		} else {
			appointmentList = appointmentRepository.findAll(paging);
		}

		Integer recordOrder = 0;
		int count = 0;
		Integer totalRecord = appointmentList.toList().size();

		List<Appointment> appointmentBefore = new ArrayList<Appointment>();
		List<Appointment> appointmentAfter = new ArrayList<Appointment>();

		for (Appointment a : appointmentList.toList()) {

			if (a.getId().toString().equals(id)) {
				recordOrder = count;
			}
			count++;
		}
		
		System.out.println("check 1");

		System.out.println("Record Order " + recordOrder.toString());

		if (recordOrder > 0) {
			for (int i = 0; i < recordOrder; i++) {
				appointmentBefore.add(appointmentList.toList().get(i));
			}
		}

		System.out.println("check 2");	
		
		System.out.println("appointment list size  " + appointmentList.toList().size());
		
		System.out.println("Total Record " + totalRecord.toString());
		
		System.out.println("Current Page " + curretPage);		

		for (int i = recordOrder + 1; i <= totalRecord - 1; i++) {
			appointmentAfter.add(appointmentList.toList().get(i));
		}

		System.out.println("check 3");		
		
		Appointment appointment = appointmentRepository.findById(Long.valueOf(id)).get();
		
		System.out.println("check 4");		
		
		model.addAttribute("appointment", new Appointment());

		model.addAttribute("appointmentListBefore", appointmentBefore);
		model.addAttribute("appointmentListAfter", appointmentAfter);
		model.addAttribute("appointmentEdit", appointment);

		try {
			model.addAttribute("sortText", "Order: " + sort.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			model.addAttribute("searchText", "Filter: " + newSearch);
		} catch (Exception e) {
			model.addAttribute("searchText", "Filter: ");
		}

		return "index";
	}

	@GetMapping("/remAppointment/{id}")
	public String remAppointment(Model model, @PathVariable String id) {

		try {

			appointmentRepository.deleteById(Long.valueOf(id));
		} catch (Exception e) {

		}

		return "redirect:/"+curretPage.toString();
	}

	
	//for Sorting 
	/*
	 * 
	 */
	@GetMapping("/order/{field}/{direction}")
	public String orderBy(Model model, @PathVariable String field, @PathVariable String direction) {

		
		
		newSearch = "";
		sort = Sort.by(Direction.ASC, field);
		Sort tempSort = Sort.unsorted();

		if (Direction.DESC.toString().equalsIgnoreCase(direction)) {
			sort = Sort.by(Direction.DESC, field);}

		if (!sortList.toString().contains(field)) {
			sortList.add(sort);}
		
		else {
			for (Sort st : sortList) {
				if (st.toString().contains(field)) {
					tempSort = st;}
			}
		}

		if (!tempSort.isUnsorted()) {
			if (!sortList.toString().contains(sort.toString())) {
				sortList.add(sort);
				System.out.println("new sort" + sort);
			}
			sortList.remove(tempSort);
		}

		sort = Sort.unsorted();

		for (Sort st : sortList) {
			if (st != null) {
				if (!sort.toString().equals(st.toString())) {
					sort = sort.and(st);
				}
			}
		}

		paging = PageRequest.of(0, recordPerPage, sort);

		this.appointmentList = appointmentRepository.findAll(paging);

		return "redirect:/";
	}

	@SuppressWarnings("static-access")
	@PostMapping("/searchAppointment")
	public String searchAppointment(Model model, @RequestParam String appointmentId, String appointmentPurpose,
			String appointmentStartDate, String appointmentStartTime, String appointmentPerson) {

		Appointment app = new Appointment();

		
		//Creating a search object  
		try {
			if (!appointmentPerson.equals(""))
				app.setPerson(appointmentPerson);
		} catch (Exception e) {
			app.setPerson(null);
		}

		try {
			if (!appointmentPurpose.equals(""))
				app.setPurpose(appointmentPurpose);
		} catch (Exception e) {
			app.setPurpose(null);
		}

		try {
			app.setStartDate(LocalDate.parse(appointmentStartDate));
		} catch (Exception e) {

		}

		try {
			app.setStartTime(LocalTime.parse(appointmentStartTime));
		} catch (Exception e) {

		}

		try {
			app.setId(Long.valueOf(appointmentId));
		} catch (Exception e) {
			app.setId(null);
		}
		
		
        //Searching fields in DB
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreNullValues();
		example = Example.of(app, matcher);
		this.appointmentList = appointmentRepository.findAll(example, paging);
		newSearch = app.toString();

		
		return "redirect:/"+curretPage.toString();

//		List<String> searchFields = new ArrayList<>();
//		searchFields.add("findBy");

//		System.out.println(app.toString());
//		if (app.getId() > 0L) {
//			if (searchFields.size() > 1) {
//				searchFields.add("And");
//			}
//			searchFields.add("Id");
//		}
//
//		if (!app.getPurpose().equals("")) {
//			if (searchFields.size() > 1) {
//				searchFields.add("And");
//			}
//			searchFields.add("Purpose");
//		}
//		if (!app.getPerson().equals("")) {
//			if (searchFields.size() > 1) {
//				searchFields.add("And");
//			}
//			searchFields.add("Person");
//		}
//		if (app.getStartDate() != null) {
//			if (searchFields.size() > 1) {
//				searchFields.add("And");
//			}
//			searchFields.add("StartDate");
//		}
//		if (app.getStartTime() != null) {
//			if (searchFields.size() > 1) {
//				searchFields.add("And");
//			}
//			searchFields.add("StartTime");
//		}

//		newSearch = searchFields.toString().replace(" ", "").replace(",", "").replace("[", "").replace("]", "");
//
//		System.out.println(newSearch);
//
//		switch (newSearch) {
//
//		case "findById":
//   		     this.appointmentList = appointmentRepository.findById(app.getId(), paging);
//			 break;
//
//		case "findByPurpose":
//			this.appointmentList = appointmentRepository.findByPurpose(app.getPurpose(), paging);
//			break;
//		case "findByPurposeAndStartDateAndStartTime":
//			this.appointmentList = appointmentRepository.findByPurposeAndStartDateAndStartTime(app.getPurpose(),
//					app.getStartDate(), app.getStartTime(), paging);
//			break;
//
//		case "findByPerson":
//			appointmentList = appointmentRepository.findByPerson(app.getPerson(), paging);
//			break;
//
//		case "findByPurposeAndPerson":
//			appointmentList = appointmentRepository.findByPurposeAndPerson(app.getPurpose(), app.getPerson(), paging);
//			break;
//
//		case "findByStartDate":
//			appointmentList = appointmentRepository.findByStartDate(app.getStartDate() , paging);
//			break;
//
//		case "findByStartTime":
//			appointmentList = appointmentRepository.findByStartTime(app.getStartTime(), paging);
//			break;
//
//		case "findByPersonAndStartDateAndStartTime":
//			appointmentList = appointmentRepository.findByPersonAndStartDateAndStartTime(app.getPerson(),
//					app.getStartDate(), app.getStartTime(), paging);
//			break;
//
//		case "findByStartDateAndStartTime":
//			appointmentList = appointmentRepository.findByStartDateAndStartTime(app.getStartDate(), app.getStartTime(), paging);
//
//			break;
//		case "findByPurposeAndPersonAndStartDateAndStartTime":
//			appointmentList = appointmentRepository.findByPurposeAndPersonAndStartDateAndStartTime(app.getPurpose(),
//					app.getPerson(), app.getStartDate(), app.getStartTime() , paging);
//			break;
//
//		default:
//			appointmentList = appointmentRepository.findAll(paging);
//			break;
//		}

//		newSearch = app.toString(); 
//
//		return "redirect:/1";
	}

}
