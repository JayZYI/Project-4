var tbMataKuliah = document.getElementById('tb_matakuliah');
var databaseRef = firebase.database().ref('matakuliah/');
var rowIndex = 1;
databaseRef.once('value', function(snapshot) {
 snapshot.forEach(function(childSnapshot) {
 var childKey = childSnapshot.key;
 var childData = childSnapshot.val();
 
 var row = tbMataKuliah.insertRow(rowIndex);
 var cellId = row.insertCell(0);
 var cellKode = row.insertCell(1);
 var cellSKS = row.insertCell(2);
 var cellNama = row.insertCell(3);
 cellId.appendChild(document.createTextNode(childKey));
 cellKode.appendChild(document.createTextNode(childData.kode));
 cellSKS.appendChild(document.createTextNode(childData.sks));
 cellNama.appendChild(document.createTextNode(childData.nama));
 rowIndex = rowIndex + 1;
 });
 var table = document.getElementById("tb_matakuliah");
 var rows = table.getElementsByTagName("tr");
 
 for (i = 0; i < rows.length; i++) {
	 var currentRow = table.rows[i];
	 var createClickHandler = function(row) {
	 return function() {
	 var cell1 = row.getElementsByTagName("td")[0];
	 var cell2 = row.getElementsByTagName("td")[1];
	 var cell3 = row.getElementsByTagName("td")[2];
	 var cell4 = row.getElementsByTagName("td")[3];
	 var id = cell1.innerHTML;
	 var kode = cell2.innerHTML;
	 var sks = cell3.innerHTML;
	 var nama = cell4.innerHTML;
	 document.getElementById('uid').value = id;
	 document.getElementById('kode').value = kode;
	 document.getElementById('sks').value = sks;
	 document.getElementById('nama').value = nama;
	 
	 };
	 };
	 currentRow.onclick = createClickHandler(currentRow);
	 }
	});