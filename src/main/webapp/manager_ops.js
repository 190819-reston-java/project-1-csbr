'use strict';

// see tables.js for BASE_URL variable.
const MANAGER_URL = `${BASE_URL}/manager`;

let menuTable = document.getElementById("menu");

/* see tables.js for how the fetchDataUtility and 
	clearDisplay functions look. */

menuTable.addEventListener("change", (event)=>{
	if (menuTable.value === "base") {
		clearDisplay();
	} else if (menuTable.value === "allemployees") {
		fetchUtility((MANAGER_URL+"/allemployees"),"GET");
	} else if (menuTable.value === "pending") {
		fetchUtility((MANAGER_URL+"/pending"),"GET");
	} else if (menuTable.value === "approved") {
		fetchUtility((MANAGER_URL+"/approved"),"GET");
	} else if (menuTable.value === "denied") {
		fetchUtility((MANAGER_URL+"/denied"),"GET");
	} else {
		reimbTable.innerHTML = "Invalid Directory.";
	}
})























/*if (selectedIndex!=0)
	document.getElementById('Request_List').innerHTML=options[selectedIndex].value;*/