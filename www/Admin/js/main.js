function reload_page(){
    window.location.reload();
    }

// Create
// function create(){
//     console.log();
//     console.log("sky is red");
//     // var brand = document.getElementById('b_create').value;
//     var brandSelect = document.getElementById('t_create');
//     var brand = brandSelect.options[brandSelect.selectedIndex].value;

//     var title = document.getElementById('ttl_create').value;

//     var model = document.getElementById('m_create').value;
//     // var cat = document.getElementById('c_create').value;
//     var categorySelect = document.getElementById('t_create');
//     var cat = categorySelect.options[categorySelect.selectedIndex].value;  

//     // var size = document.getElementById('si_create').value;
//     var sizeSelect = document.getElementById('si_create');
//     var size = sizeSelect.options[categorySelect.selectedIndex].value;
  
//     var stock = document.getElementById('st_create').value;
//     var image = document.getElementById('img_create').value;

//     var price = document.getElementById('pr_create').value;
//     var uid = firebase.database().ref().child('novel').push().key;
//     var data = {
//         title : title,
//         cover : image,
//         views : model,
//         rating : cat,
//         chapters : size,
//         readTimes : stock,                                                                                                                                                                                   
//         tags : 
//         }
//     var updates = {};
//     updates['/shoe/' + uid] = data;
//     firebase.database().ref().update(updates);
//     alert('New Shoe has created successfully!');
//     reload_page();
//     }
function create() {
    console.log();
    console.log("create function called");
  
    var title = document.getElementById('ttl_create').value;
    var views = document.getElementById('v_create').value;
    var chapters = document.getElementById('chpt_create').value;
    var readTimes = document.getElementById('rt_create').value;
    var categorySelect = document.getElementById('c_create');
    var tag = categorySelect.options[categorySelect.selectedIndex].value;
  
    // Get the selected image file
    var imageFile = document.getElementById('imageFile').files[0];
  
    // Create a storage reference to store the image file
    var storageRef = firebase.storage().ref().child('novelCovers/' + imageFile.name);
  
    // Upload the image file to Firebase Storage
    storageRef.put(imageFile)
      .then(function(snapshot) {
        console.log('Image uploaded successfully');
  
        // Get the download URL of the uploaded image
        storageRef.getDownloadURL()
          .then(function(url) {
            // Create a new novel object with the image URL
            var novel = {
              title: title,
              novelCover: url,
              views: views,
              chapters: chapters,
              readTimes: readTimes,
              tag: tag
            };
  
            // Push the new novel object to the database
            var novelRef = firebase.database().ref('novel').push();
            novelRef.set(novel)
              .then(function() {
                alert('New Novel has been created successfully!');
                // Reload the page or perform any other desired action
                // reload_page();
              })
              .catch(function(error) {
                console.error('Error creating novel:', error);
              });
          })
          .catch(function(error) {
            console.error('Error getting download URL:', error);
          });
      })
      .catch(function(error) {
        console.error('Error uploading image:', error);
      });
  }
  
  

    function tagcreate(){
        console.log();
        console.log("sky is red");
        var model = document.getElementById('t_create').value;
        var uid = firebase.database().ref().child('Category').push().key;
        var data = {
            CatName: model
            }
        var updates = {};
        updates['/Category/' + uid] = data;
        firebase.database().ref().update(updates);
        alert('New tag has created successfully!');
        reload_page();
        }

    function chapcreate(){
        console.log();
        console.log("sky is red");
        var novel = document.getElementById('n_create').value;
        var ctitle = document.getElementById('chp_create').value;
        var content = document.getElementById('t_create').value;

        var novel = document.getElementById('n_create').value;

        var uid = firebase.database().ref().child('chap').push().key;
        var data = {
            novel : novel,
            chapTitle : ctitle,
            content : content
            }
        var updates = {};
        updates['/chap/' + uid] = data;
        firebase.database().ref().update(updates);
        alert('New chapter has created successfully!');
        reload_page();
        }    
            
    


    function showSelectedValues() {
        var brandSelect = document.getElementById('b_create');
        var brand = brandSelect.options[brandSelect.selectedIndex].value;
        console.log("Selected Brand:", brand);
      
        var model = document.getElementById('m_create').value;
        console.log("Model:", model);
      
        var categorySelect = document.getElementById('c_create');
        var category = categorySelect.options[categorySelect.selectedIndex].value;
        console.log("Selected Category:", category);
      
        var sizeSelect = document.getElementById('si_create');
        var size = sizeSelect.options[categorySelect.selectedIndex].value;
  
        console.log("Size:", size);
      
        var stock = document.getElementById('st_create').value;
        console.log("Stock:", stock);
      
        var price = document.getElementById('pr_create').value;
        console.log("Price:", price);
      }
      


function update(){
    console.log();
	//  var brand = document.getElementById('b_update').value;
     var brandSelect = document.getElementById('b_update');
     var brand = brandSelect.options[brandSelect.selectedIndex].value;

	 var model = document.getElementById('m_update').value;

	//  var color = document.getElementById('c_update').value;
     var categorySelect = document.getElementById('c_update');
     var cat = categorySelect.options[categorySelect.selectedIndex].value; 

    //  var size = document.getElementById('si_update').value;
     var sizeSelect = document.getElementById('si_update');
     var size = sizeSelect.options[categorySelect.selectedIndex].value;

     var stock = document.getElementById('st_update').value;
     var price = document.getElementById('p_update').value;
     var imgurl = document.getElementById('urli_update').value;
     
     var uid = document.getElementById('r_uid').value;

	 
	 var data = {
	 brand: brand,
	 model: model,
	 category: cat,
     size: size,
     stock: stock,
     price: price,
     URLImg: imgurl
	 }
	 
	 var updates = {};
	 updates['/shoe/' + uid] = data;
	 firebase.database().ref().update(updates);
	 
	 alert('data updated successfully!');
	 
	 reload_page();

	 }
	 
	 function delete_shoe(){
	 var uid = document.getElementById('r_uid').value;
	 
	 firebase.database().ref().child('/shoe/' + uid).remove();
	 alert('data deleted successfully!');
	 reload_page();
	 }
	 

function formatPrice(input) {
    // Remove existing commas from the input value
    var value = input.value.replace(/,/g, '');
  
    // Format the value with comma separators for thousands
    var formattedValue = new Intl.NumberFormat().format(value);
  
    // Set the formatted value back to the input field
    input.value = formattedValue;
    }

