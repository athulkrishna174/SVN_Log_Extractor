window.onload = function() {
	const check = document.getElementById('optionRemoveUnwanted');
	const outputFormat = document.getElementById('excelFormat');
	hideUnHideBlock(check, 'requiredFormatDiv');
	
	if(outputFormat.checked){	
		changeOutputFormat(outputFormat);
	}
}


function enableDisableOtherInput(check, inputId) {
	let inputField = document.getElementById(inputId);

	if (check.checked) {
		inputField.disabled = false;
	} else {
		inputField.disabled = true;
		inputField.value = '';
	}
}

function hideUnHideBlock(check, blockId) {
	let blockField = document.getElementById(blockId);

	if (check.checked) {
		blockField.style.display = 'block';
	} else {
		blockField.style.display = 'none';
	}
}

function validateExtractForm() {
	const optionRemoveUnwanted = document.getElementById("optionRemoveUnwanted");
	const requiredFormats = document.querySelectorAll('input[name="requiredFormats"]');
	
	const outputFormat = document.getElementById('excelFormat');
	const selectedProjects = document.querySelectorAll('input[name="selectedProjects"]');
	
	let isValid = false;

	if (optionRemoveUnwanted.checked) {
		for (let required of requiredFormats) {
			if (required.checked) {
				isValid = true;
				break;
			}
		}
		if (!isValid) {
			alert("Please Select Atleast One Required Format.");
			return false;
		}
	}
	
	if (outputFormat.checked) {
		isValid = false
		for (let selectedProject of selectedProjects) {
			if (selectedProject.checked) {
				isValid = true;
				break;
			}
		}
		if (!isValid) {
			alert("Please Select Atleast One Projrct.");
			return false;
		}
	}

	return true;
}

function changeOutputFormat(outputFormat){
	const projectNameTable = document.getElementById('projectNameTable');
	const selectedProjectsTable = document.getElementById('selectedProjectsTable');
	
	if(".xlsx" === outputFormat.value){
		projectNameTable.style.display = 'none';
		selectedProjectsTable.style.display = 'block';
	} else {
		projectNameTable.style.display = 'block';
		selectedProjectsTable.style.display = 'none';
	}
}