'use strict';

// see tables.js for BASE_URL variable.
const EMPLOYEE_URL = `${BASE_URL}/employee`;

let menuTable = document.getElementById("menu");

/* see tables.js for how the fetchDataUtility and 
 	clearDisplay functions look. */

menuTable.addEventListener("change", (event)=>{
	if (menuTable.value === "base") {
		clearDisplay();
	} else if (menuTable.value === "allreqs") {
		fetchDataUtility(EMPLOYEE_URL+"/allreqs");
	} else if (menuTable.value === "pending") {
		fetchDataUtility(EMPLOYEE_URL+"/pending");
	} else if (menuTable.value === "approved") {
		fetchDataUtility(EMPLOYEE_URL+"/approved");
	} else if (menuTable.value === "denied") {
		fetchDataUtility(EMPLOYEE_URL+"/denied");
	} else {
		reimbTable.innerHTML = "Invalid Directory.";
	}
})























/*if (selectedIndex!=0)
	document.getElementById('Request_List').innerHTML=options[selectedIndex].value;*/