function scanItemQRCode(entryType) {
	$('#scanModal').modal('show');

	var scanCodeInput = document.getElementById('scanCode');
	const html5QrCode = new Html5Qrcode("qr-reader");
	const config = { fps: 10, qrbox: { width: 250, height: 250 } };
	const onScanSuccess = (decodedText, decodedResult) => {
		console.log(`Scan result = ${decodedText}`, decodedResult);

		html5QrCode.stop().then((ignore) => {}).catch((err) => {
			console.log(`Error stopping scanner. Reason: ${err}`)
		});

		$('#scanModal').modal('hide');
		if(entryType == 'Stock In') scanCodeInput.value = decodedText;
		else populateDataIfScanCodeExists(decodedText);

	}

	html5QrCode.start({ facingMode: "environment" }, config, onScanSuccess);

	const fileinput = document.getElementById('qr-input-file');
	fileinput.addEventListener('change', e => {

		html5QrCode.stop().then((ignore) => {}).catch((err) => {console.log(`Error stopping scanner. Reason: ${err}`)});

		if (e.target.files.length == 0) {
			return;
		}

		const imageFile = e.target.files[0];

		html5QrCode.scanFile(imageFile, true)
		.then(decodedText => {
			$('#scanModal').modal('hide');
			if(entryType == 'Stock In') scanCodeInput.value = decodedText;
			else populateDataIfScanCodeExists(decodedText);

		})
		.catch(err => {
			$('#scanModal').modal('hide');
			alert(`Error scanning file. Reason: ${err}`);
		});
	});

};

function populateDataIfScanCodeExists(scanCode){
	var checkCodeUrl = '/checkIfScanCodeExistsForStockIn';

	if (scanCode) {
		$.ajax({
			url : checkCodeUrl,
			data : { "scanCode" : scanCode },
			success : function(result) {
				var result = JSON.parse(result);
				if(result == null) return;
				
				var option = document.createElement("option");
				option.text = result[1];
				option.value = result[1];
				option.selected = 'selected';
				document.getElementById("productNames").add(option);
				
				var option1 = document.createElement("option");
				option1.text = result[2];
				option1.value = result[2];
				option1.selected = 'selected';
				document.getElementById("sizes").add(option1);
				
				var option2 = document.createElement("option");
				option2.text = result[3];
				option2.value = result[3];
				option2.selected = 'selected';
				document.getElementById("colours").add(option2);
				
				var option3 = document.createElement("option");
				option3.text = result[4];
				option3.value = result[0];
				option3.selected = 'selected';
				document.getElementById("brands").add(option3);
				
				var quantityTag = `<input type="text" class="form-control" id="quantity" placeholder="Quantity" onChange="calculateAmount()">`;
				var maxQuantityTag = `<input type="hidden" id="maxQuantity" value="`;
				$('#quantityOuter').html(quantityTag + maxQuantityTag + result[5] + `">`);

			}
		});
	}
}


$(document).ready(() => {
	document.getElementById('scanCode').addEventListener('blur', function(event) {
		console.log('Onblur even occured for scanCode');
		
		if(document.getElementsByName("stockOutScan").length > 0) {
			console.log('Populating data for stock out');
			populateDataIfScanCodeExists(event.target.value);
		}
	});

	document.getElementById('scanCodeBtn').addEventListener('click', function(event) {
		console.log('Onclick even occured for scan button');

		if(document.getElementsByName("stockInScan").length > 0) {
			console.log('Scan code for stock in');
			scanItemQRCode('Stock In');

		} else {
			console.log('Scan code for stock out');
			scanItemQRCode('Stock Out');
		}
	});

})



$("#scanModal").on('hidden.bs.modal', function () {
	$(this).data('bs.modal', null);
});

