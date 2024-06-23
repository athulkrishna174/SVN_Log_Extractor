window.onload = function() {
	let check = document.getElementById('optionRemoveUnwanted');
	hideUnHideBlock(check, 'requiredFormatDiv');
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
	let isSelectedReqFormat = false;


	if (optionRemoveUnwanted.checked) {
		for (let required of requiredFormats) {
			if (required.checked) {
				isSelectedReqFormat = true;
				break;
			}
		}
		if (!isSelectedReqFormat) {
			alert("Please Select Atleast One Required Format.");
			return false;
		}
	}

	return true;
}