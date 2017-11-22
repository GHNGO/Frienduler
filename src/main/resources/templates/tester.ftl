
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <!--





  -->
  <title>Event</title>

  <meta name="description" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1">


  <link rel="stylesheet" href="../js/bootstrap.min.css">
  <link rel="stylesheet" href="../js/bootstrap-theme.min.css">
  <link rel="stylesheet" href="../js/fontAwesome.css">
  <link rel="stylesheet" href="../js/hero-slider.css">
  <link rel="stylesheet" href="../js/owl-carousel.css">
  <link rel="stylesheet" href="../js/datepicker.css">
  <link rel="stylesheet" href="../js/tooplate-style.css">
  <link rel="stylesheet" href="../js/sidenav.css">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">

  <script src="../js/modernizr-2.8.3-respond-1.4.2.min.js"></script>
</head>

<body class="pritty-body">
<div id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <a>Friend 1</a>
  <a>Friend 2</a>
  <a>Friend 3</a>
  <a>Wumbo</a>
</div>
<span style="color:white;cursor:pointer;" onclick="openNav()"> <object align="right"><span style="font-size: 17px;padding-right: 5px;"><br><br>&#9776; Friends List &emsp;&emsp;&emsp;</span></object></span>


  <section class="banner" id="top">
    <div id = "main">
      <div class="container">
      <div class="row">
        <div class="col-md-7 col-md-offset-2">
            <div class="logo">
              <img src="../js/logo.png" width="350" height="150">
            </div>
          
        </div>
        <div class="col-md-6 col-md-offset-3">
        
          <section id="first-tab-group" class="tabgroup">
            <div id="tab1">
              <div class="submit-form">
                <form id="form-submit" action="" method="get">
                  <div class="row">
                    <div class="col-md-12">
                      <fieldset>
                        <label>Create an event:</label>
                        <input type="text" class="form-control date" placeholder="Event name..." required="" onchange='this.form.()'>
                      </fieldset>
                    </div>
                    <div class="col-md-6">
                      <fieldset>
                        <label for="from">From:</label>
                        <select required name='from' onchange='this.form.()'>
                          <option value="">Select a Time...</option>
                          <option value="12:00 am">12:00 am</option>
                          <option value="1:00 am">1:00 am</option>
                          <option value="2:00 am">2:00 am</option>
                          <option value="3:00 am">3:00 am</option>
                          <option value="4:00 am">4:00 am</option>
                          <option value="5:00 am">5:00 am</option>
                          <option value="6:00 am">6:00 am</option>
                          <option value="7:00 am">7:00 am</option>
                          <option value="8:00 am">8:00 am</option>
                          <option value="9:00 am">9:00 am</option>
                          <option value="10:00 am">10:00 am</option>
                          <option value="11:00 am">11:00 am</option>

                          <option value="12:00 pm">12:00 pm</option>
                          <option value="1:00 pm">1:00 pm</option>
                          <option value="2:00 pm">2:00 pm</option>
                          <option value="3:00 pm">3:00 pm</option>
                          <option value="4:00 pm">4:00 pm</option>
                          <option value="5:00 pm">5:00 pm</option>
                          <option value="6:00 pm">6:00 pm</option>
                          <option value="7:00 pm">7:00 pm</option>
                          <option value="8:00 pm">8:00 pm</option>
                          <option value="9:00 pm">9:00 pm</option>
                          <option value="10:00 pm">10:00 pm</option>
                          <option value="11:00 pm">11:00 pm</option>

                        </select>
                      </fieldset>
                    </div>
                    <div class="col-md-6">
                      <fieldset>
                        <label for="to">To:</label>
                        <select required name='to' onchange='this.form.()'>
                          <option value="">Select a Time...</option>
                          <option value="12:00 am">12:00 am</option>
                          <option value="1:00 am">1:00 am</option>
                          <option value="2:00 am">2:00 am</option>
                          <option value="3:00 am">3:00 am</option>
                          <option value="4:00 am">4:00 am</option>
                          <option value="5:00 am">5:00 am</option>
                          <option value="6:00 am">6:00 am</option>
                          <option value="7:00 am">7:00 am</option>
                          <option value="8:00 am">8:00 am</option>
                          <option value="9:00 am">9:00 am</option>
                          <option value="10:00 am">10:00 am</option>
                          <option value="11:00 am">11:00 am</option>

                          <option value="12:00 pm">12:00 pm</option>
                          <option value="1:00 pm">1:00 pm</option>
                          <option value="2:00 pm">2:00 pm</option>
                          <option value="3:00 pm">3:00 pm</option>
                          <option value="4:00 pm">4:00 pm</option>
                          <option value="5:00 pm">5:00 pm</option>
                          <option value="6:00 pm">6:00 pm</option>
                          <option value="7:00 pm">7:00 pm</option>
                          <option value="8:00 pm">8:00 pm</option>
                          <option value="9:00 pm">9:00 pm</option>
                          <option value="10:00 pm">10:00 pm</option>
                          <option value="11:00 pm">11:00 pm</option>

                        </select>
                      </fieldset>
                    </div>
                    <div class="col-md-6">
                      <fieldset>
                        <label for="Start Date">Start date:</label>
                        <input type="text" class="form-control date" placeholder="Select date..." required="" onchange='this.form.()'>
                      </fieldset>
                    </div>
                    <div class="col-md-6">
                      <fieldset>
                        <label for="return">End date:</label>
                        <input name="return" type="text" class="form-control date" id="return" placeholder="Select date..." required="" onchange='this.form.()'>
                      </fieldset>
                    </div>
                    
                    <div class="col-md-6">
                         <fieldset>
                          	<button class="btn" type="submit" required="required" onchange='this.form.()'>Repeat every day</button>
                         </fieldset>
                    </div>
                    
                    <div class="col-md-6">
                      <fieldset>
                        <button type="submit" id="form-submit" class="btn">Set Event Now</button>
                      </fieldset>
                    </div>
                     
                  </div>
                </form>
              
             	 </div>
              
              <div>
              </div>
                <div class="page-direction-button">
             		<a href="/index.html"><i class="fa fa-phone"></i>Return</a>
           		</div>
            </div>
          </section>
        </div>
      </div>
    </div>
    </div>
  </section>
  <script>
    function openNav() {
      document.getElementById("mySidenav").style.width = "250px";
      document.getElementById("main").style.marginRight = "250px";
    }

    function closeNav() {
      document.getElementById("mySidenav").style.width = "0";
      document.getElementById("main").style.marginRight = "0";
    }
  </script>
</body>
</html>