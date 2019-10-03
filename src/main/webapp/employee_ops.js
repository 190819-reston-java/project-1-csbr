'use strict';

// see tables.js for BASE_URL variable.
const EMPLOYEE_URL = `${BASE_URL}/employee`;

let menuTable = document.getElementById("menu");
let contactForm = document.getElementById("contact-edit");

/* see tables.js for how the fetchDataUtility and 
 	clearDisplay functions look. */

let contactUpdate = (form) => {
	let contactInfo = {};
	contactInfo.first_name = form.first_name.value || "First";
	contactInfo.last_name = form.last_name.value || "Last";
	contactInfo.email = form.email.value || "ayyy@lmao.ha";
	contactInfo.street_address = form.street_address.value || "";
	contactInfo.city = form.city.value || "";
	contactInfo.country = (form.state.value + "," 
			+ form.zip.value + ",USA") || "";
	return contactInfo;
}

menuTable.addEventListener("change", (event)=>{
	if (menuTable.value === "base") {
		clearDisplay();
	} else if (menuTable.value === "allreqs") {
		fetchDataUtility(EMPLOYEE_URL+"/allreqs",true);
	} else if (menuTable.value === "pending") {
		fetchDataUtility(EMPLOYEE_URL+"/pending",true);
	} else if (menuTable.value === "approved") {
		fetchDataUtility(EMPLOYEE_URL+"/approved",true);
	} else if (menuTable.value === "denied") {
		fetchDataUtility(EMPLOYEE_URL+"/denied",true);
	} else {
		reimbTable.innerHTML = "Invalid Directory.";
	}
})

contactForm.addEventListener("submit", (event)=>{
	console.log(contactUpdate(contactForm));
	fetchNewData((EMPLOYEE_URL+"/updateContacts"),
		contactUpdate(contactForm),"Updat(e|ing) contacts");
})



















/*if (selectedIndex!=0)
	document.getElementById('Request_List').innerHTML=options[selectedIndex].value;*/