'use strict';

// see tables.js for BASE_URL variable.
const EMPLOYEE_URL = `${BASE_URL}/employee`;

let contactForm = document.getElementById("contact-edit");
let filler = document.getElementsByTagName("div");

/* see tables.js for how the fetchDataUtility and 
 	clearDisplay functions look. */

var nameNew = document.createElement("div");
nameNew.innerText = "sdfhj";
	
document.querySelector("#name").appendChild(nameNew);





let contactView = (form) => {
	
	
	filler[0].id.innerText = form;
	

}



















/*if (selectedIndex!=0)
	document.getElementById('Request_List').innerHTML=options[selectedIndex].value;*/