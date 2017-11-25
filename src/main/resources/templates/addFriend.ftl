
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
  <link rel="stylesheet" href="../js/button.css">

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

<section class="banner" id="top">
  <br>
  <span style="color:white;cursor:pointer;" onclick="openNav()"> <object align="right"><input type="button" class="buttoner" value="&#9776; Friends List"/></object></span>
  <object align="left"><a href="/Frienduler/user/${currentUser}/createEvent"><input type="button" class="buttoner" value="Create Event"/></a></object>
  <object align="center"><a href="/Frienduler/user/${currentUser}/compare"><input type="button" class="buttoner" value="Compare Schedules"/></a></object>
  <div id = "main">
    <div class="container">
      <div class="col-md-6 ">
        <section id="first-tab-group" class="tabgroup">
          <div id="tab1">
            <div class="submit-form">
              <form id="form-submit" action="" method="get">
                <div class="row">
                  <div class="col-md-12">
                    <fieldset>
                      <label>Add friends:</label>
                      <input type="text" class="form-control date" placeholder="Friend name..." required="" onchange='this.form.()'>
                    </fieldset>
                  </div>
                  <div class="col-md-6">
                    <fieldset>
                      <button type="submit" id="form-submit" class="btn">Add Friend</button>
                    </fieldset>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </section>
      </div>
      <div class="col-md-6 ">

        <section id="Friends-group" class="tabgroup">
          <div id="tab2">
            <div class="submit-form">
              <form id="form-submit" action="" method="get">
                <div class="row">
                  <div class="col-md-12">
                    <fieldset>
                      <label>Create a group:</label>
                      <input type="text" class="form-control date" placeholder="Group name..." required="" onchange='this.form.()'>
                    </fieldset>
                  </div>
                  <div class="col-md-6">
                    <fieldset>
                      <label for="from">Or Select existing group</label>
                      <select required onchange='this.form.()'>
                        <option value="">Select a Group...</option>
                        <option value="(groupname)">Ghngo</option>
                          <option value="(groupname2)">Wildthing</option>

                      </select>
                    </fieldset>
                  </div>
                  <div class="col-md-7">
                    <fieldset>
                      <input type="text" class="form-control date" placeholder="friends name..." required="" onchange='this.form.()'>
                    </fieldset>
                  </div>
                  <div class="col-md-6">
                    <fieldset>
                      <button type="submit" id="form-submit" class="btn">Add to group</button>
                    </fieldset>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </section>
      </div>

    </div>
  </div>
  <div class="col-md-6 col-md-offset-3" >
    <object align="center"><a href="/Frienduler/"><input type="button" class="buttoner" value="Return to Home"/></a></object>
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
