'use strict';

const BASE_URL = "/project-1-CsBr/tables";
const EMPLOYEE_URL = `${BASE_URL}/employee`;
const MANAGER_URL = `${BASE_URL}/manager`;
const REIMB_URL = `${BASE_URL}/reimb`;

let menuTable = document.getElementById("menu");
let reimbTable = document.getElementById("data");

let clearDisplay = () => {
	reimbTable.innerHTML = "";
}

let createList = (reimbs) => {
		let row = document.createElement("tr");
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");
		
	  td1.innerText = `${reimbs.origIssuer}`;
	  row.appendChild(td1);
	  reimbTable.appendChild(row);
	  td2.innerText = `${reimbs.status}`;
	  row.appendChild(td2);
	  reimbTable.appendChild(row);
	  td3.innerText = `${reimbs.balance}`;
	  row.appendChild(td3);
	  reimbTable.appendChild(row);
	  td4.innerText = `${reimbs.resolver}`;
	  row.appendChild(td4);
	  reimbTable.appendChild(row);
	  
	  //clearDisplay();
}

let fetchUtility = (url,mthd) => {
	fetch(url, { method: mthd })
    .then((response)=>{
      return response.json();
    })
    .then((reimbJson)=>{
      //clearDisplay();
      for(let reimbs in reimbJson) {
        console.log(reimbJson[reimbs]);
        createList(reimbJson[reimbs]);
      }
    })
    .catch(console.log);
}

menuTable.addEventListener("change", (event)=>{
	if (menuTable.value === "base") {
		clearDisplay();
	} else if (menuTable.value === "allreqs") {
		fetchUtility((EMPLOYEE_URL+"/allreqs"),"GET");
	} else if (menuTable.value === "pending") {
		fetchUtility((EMPLOYEE_URL+"/pending"),"GET");
	} else if (menuTable.value === "approved") {
		fetchUtility((EMPLOYEE_URL+"/approved"),"GET");
	} else if (menuTable.value === "denied") {
		fetchUtility((EMPLOYEE_URL+"/denied"),"GET");
	} else {
		reimbTable.innerHTML = "Invalid Directory.";
	}
})
	  //reimbsDisplay.append(li);

/*for (let i = 0; 
	document.
	getElementsByTagName("option").
	length.value; ++i)
*/























/*if (selectedIndex!=0)
	document.getElementById('Request_List').innerHTML=options[selectedIndex].value;*/