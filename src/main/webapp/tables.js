'use strict';

const BASE_URL = "/project-1-CsBr/tables";
/*const EMPLOYEE_URL = `${BASE_URL}/employee`;
const MANAGER_URL = `${BASE_URL}/manager`;
const REIMB_URL = `${BASE_URL}/reimb`;*/

let reimbTable = document.getElementById("data");

let clearDisplay = () => {
	reimbTable.innerHTML = "";
}

let createList = (reimbData) => {
		let row = document.createElement("tr");
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");
		
	  td1.innerText = `${reimbData.origIssuer}`;
	  row.appendChild(td1);
	  reimbTable.appendChild(row);
	  td2.innerText = `${reimbData.status}`;
	  row.appendChild(td2);
	  reimbTable.appendChild(row);
	  td3.innerText = `${reimbData.balance}`;
	  row.appendChild(td3);
	  reimbTable.appendChild(row);
	  td4.innerText = `${reimbData.resolver}`;
	  row.appendChild(td4);
	  reimbTable.appendChild(row);
}

let fetchDataUtility = (url) => {
	fetch(url, 
		{ method: "GET" })
    .then((response)=>{
    	return response.json();
    })
    .then((reimbJson)=>{
    	clearDisplay();
    	for(let reimbs in reimbJson) {
    		console.log(reimbJson[reimbs]);
    		createList(reimbJson[reimbs]);
      }
    })
    .catch(console.log);
}

let fetchNewData = (url,mthd,context) => {
	fetch(url,	
		{ method: "POST", body: JSON.stringify(mthd) })
	.then((response)=> {
		console.log(response);
		if(response.status >= 200 && response.status < 300) {
			console.log(context + " successful");
		} else {
		    alert("Failed to " + context);
		}
	})
	.catch(console.error);
} 























/*if (selectedIndex!=0)
	document.getElementById('Request_List').innerHTML=options[selectedIndex].value;*/