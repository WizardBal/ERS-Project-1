/**
 * 
 */
window.onload = function() {
	document.getElementById("b1").addEventListener("click", showReims);
}

function showReims() {
	const xhr = new XMLHttpRequest();
	//xhr.responseType = 'text';
	xhr.responseType = 'json';
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let rJSON = xhr.response;
			//JSON.parse(rJSON);
			//console.log('response');
			DOMManipulation(rJSON);
		}
	};

	xhr.open('POST', 'http://localhost:8081/Project1-mk/employeereimserv');
	xhr.send();
	//e.preventDefault();
}


function DOMManipulation(reimJSON) {
	console.log(reimJSON)
	var table = document.createElement("table"), row, cellAmount, cellAuthor, cellDescription, cellReimId, cellStatus, cellReimType, cellResolved, cellResolver, cellSubmitted;
	document.getElementById("table1").appendChild(table);
	var rId, resolver, amount, submitted, descrip, status, rType, resolved, author;
	reimJSON.forEach((obj) => {
		row = document.createElement("tr");
		rId = document.createElement("td");
		submitted = document.createElement("td");
		resolver = document.createElement("td");
		amount = document.createElement("td");
		author = document.createElement("td");
		descrip = document.createElement("td");
		status = document.createElement("td");
		rType = document.createElement("td");
		resolved = document.createElement("td");
		cellAmount = document.createElement("td");
		cellAuthor = document.createElement("td");
		cellDescription = document.createElement("td");
		cellReimId = document.createElement("td");
		cellStatus = document.createElement("td");
		cellReimType = document.createElement("td");
		cellResolved = document.createElement("td");
		cellResolver = document.createElement("td");
		cellSubmitted = document.createElement("td");
		cellResolver.innerHTML = obj.resolver + " --";
		cellAmount.innerHTML = obj.amount + " --";
		cellAuthor.innerHTML = obj.author + " --";
		if(obj.description != null){
		cellDescription.innerHTML = obj.description + " --";
		}
		cellReimId.innerHTML = obj.reimId + " --";
		cellStatus.innerHTML = obj.reimStatus + " --";
		cellReimType.innerHTML = obj.reimType + " --";
		rId.innerHTML = "Reimbursement ID: "
		author.innerHTML = "--  Author: "
		resolver.innerHTML = "--  ResolverID: "
		amount.innerHTML = "--  Amount: "
		descrip.innerHTML = "--  Description: "
		status.innerHTML = "--  Status: "
		rType.innerHTML = "-- Type: "
		resolved.innerHTML = "--  Resolved on: "
		submitted.innerHTML = "--  Submitted on: "
		if(obj.resolved != null){
		var d = new Date(obj.resolved);
		var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
		var hours = (d.getHours() < 10) ? "0" + d.getHours() : d.getHours();
		var minutes = (d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes();
		var formattedTime = hours + ":" + minutes;
		formattedDate = formattedDate + " " + formattedTime;
		cellResolved.innerHTML = formattedDate;
		}
		var d = new Date(obj.submitted);
		var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
		var hours = (d.getHours() < 10) ? "0" + d.getHours() : d.getHours();
		var minutes = (d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes();
		var formattedTime = hours + ":" + minutes;
		formattedDate = formattedDate + " " + formattedTime;
		cellSubmitted.innerHTML = formattedDate;
		table.appendChild(row);
		row.appendChild(rId)
		row.appendChild(cellReimId);
		row.appendChild(resolver)
		row.appendChild(cellResolver);
		row.appendChild(amount)
		row.appendChild(cellAmount);
		row.appendChild(author)
		row.appendChild(cellAuthor);
		row.appendChild(descrip)
		row.appendChild(cellDescription);
		row.appendChild(status)
		row.appendChild(cellStatus);
		row.appendChild(rType)
		row.appendChild(cellReimType);
		row.appendChild(resolved)
		row.appendChild(cellResolved);
		row.appendChild(submitted)
		row.appendChild(cellSubmitted);
	})
}

/*function DOMManipulation(reimJSON) {
	console.log(reimJSON)
	var table = document.createElement("table"), row, cellAmount, cellAuthor, cellDescription, cellReimId, cellStatus, cellReimType, cellResolved, cellResolver, cellSubmitted;
	document.getElementById("table1").appendChild(table);
	reimJSON.forEach((obj) => {
		row = document.createElement("tr");
		cellAmount = document.createElement("td");
		cellAuthor = document.createElement("td");
		cellDescription = document.createElement("td");
		cellReimId = document.createElement("td");
		cellStatus = document.createElement("td");
		cellReimType = document.createElement("td");
		cellResolved = document.createElement("td");
		cellResolver = document.createElement("td");
		cellSubmitted = document.createElement("td");
		cellResolver.innerHTML = obj.resolver;
		cellAmount.innerHTML = obj.amount;
		cellAuthor.innerHTML = obj.author;
		cellDescription.innerHTML = obj.description;
		cellReimId.innerHTML = obj.reimId;
		cellStatus.innerHTML = obj.reimStatus;
		cellReimType.innerHTML = obj.reimType;
		
		if(obj.resolved != null){
		var d = new Date(obj.resolved);
		var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
		var hours = (d.getHours() < 10) ? "0" + d.getHours() : d.getHours();
		var minutes = (d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes();
		var formattedTime = hours + ":" + minutes;
		formattedDate = formattedDate + " " + formattedTime;
		cellResolved.innerHTML = formattedDate;
		}
		var d = new Date(obj.submitted);
		var formattedDate = d.getDate() + "-" + (d.getMonth() + 1) + "-" + d.getFullYear();
		var hours = (d.getHours() < 10) ? "0" + d.getHours() : d.getHours();
		var minutes = (d.getMinutes() < 10) ? "0" + d.getMinutes() : d.getMinutes();
		var formattedTime = hours + ":" + minutes;
		formattedDate = formattedDate + " " + formattedTime;
		cellSubmitted.innerHTML = formattedDate;
		table.appendChild(row);
		row.appendChild(cellReimId);
		row.appendChild(cellResolver);
		row.appendChild(cellAmount);
		row.appendChild(cellAuthor);
		row.appendChild(cellDescription);
		row.appendChild(cellStatus);
		row.appendChild(cellReimType);
		row.appendChild(cellResolved);
		row.appendChild(cellSubmitted);
	})
}*/