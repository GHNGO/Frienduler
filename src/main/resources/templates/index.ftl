<!-- Website Theme: MINIMAL by BlackTie.co -->

<!DOCTYPE html>
<html lang="en" xmlns:padding-bottom="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="shortcut icon" href="../static/assets/img/calendar-white.png">

  <title>Frienduler</title>

  <!-- Bootstrap core CSS -->
  <link href="../static/assets/css/bootstrap.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="../static/assets/css/landingpage.css" rel="stylesheet">
  <link rel="stylesheet" href="../static/assets/css/font-awesome.min.css">

  <script src="../static/assets/js/jquery.min.js"></script>
  <script src="../static/assets/js/modernizr.custom.js"></script>


  <link href='http://fonts.googleapis.com/css?family=Oswald:400,300,700' rel='stylesheet' type='text/css'>
  <link href='http://fonts.googleapis.com/css?family=EB+Garamond' rel='stylesheet' type='text/css'>
  <link href="https://fonts.googleapis.com/css?family=Abril+Fatface" rel="stylesheet" type="text/css">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
  <![endif]-->
</head>

<body data-spy="scroll" data-offset="0" data-target="#theMenu">
<!-- Menu -->
<nav class="menu" id="theMenu">
  <div class="menu-wrap">
    <h1 class="logo"><a href="../static">Frienduler</a></h1>
    <i class="icon-remove menu-close"></i>
    <a href="/" class="smoothScroll">Home</a>
    <a href="/" class="smoothScroll">Login</a>
    <a href="/" class="smoothScroll">FAQ</a>
  </div>

  <!-- Menu button -->
  <div id="menuToggle"><i class="icon-reorder"></i></div>
</nav>

<!-- ========== HEADER SECTION ========== -->
<section id="home">
  <div id="headerwrap">
    <div class="container">
      <div class="logo">
        <img src="../static/assets/img/calendar-white.png" width="80" height="71">
      </div>
      <div class="row">
        <h1>
          <font color = "FFFFFF">Frienduler</font>
        </h1>
        <h3>
          <font color = "FFFFFF">The Scheduler with Friends</font>
        </h3>
        <br>
        <br>
        <div class="col-lg-6 col-lg-offset-3">
          <!-- TODO: make the login field communicate w/ back end -->
          <h2>
            <font color = "FFFFFF">Login</font>
          </h2>
          <form onSubmit="return userInput();">
            <p>
              <input type="text" name="login" value="" placeholder="Username" id="userId">
            </p>
          </form>
        </div>
      </div>
    </div>	<!-- /container -->
  </div>	<!-- /headerwrap -->
</section>

<section id="faq" name="faq"></section>
<div id="body">
  <div class="container">
    <div class="row">
      <h3>FAQ</h3>
      <p class="centered"><i class="icon icon-circle"></i><i class="icon icon-circle"></i><i class="icon icon-circle"></i></p>

      <!-- INTRO INFORMATION -->
      <div class="col-lg-6 col-lg-offset-3">
        <h1>What is Frienduler?</h1>
        <p>A web service created by five students at Cal Poly Pomona to store schedules and generate times where friends can hang out, so they don't have to figure it out themselves.</p>
        <h1>How do I log in?</h1>
        <p>Simply enter your username in the box above.</p>
        <h1>Why can't I see my events?
          These events shown aren't mine.</h1>
        <p>Check to make sure that you entered the correct user ID, case sensitive.</p>
        <h1>When is Frienduler going to be finished?</h1>
        <p>Hopefully before week 10, am I right?</p>
        <br>
        <br>
        <br>
      </div>
    </div>
  </div><!-- /container -->
</div><!-- /f -->

<!-- Footer -->
<footer id="footer">
  <div id="f">
    <span class="copyright">&copy; GHNGO, <script>document.write(new Date().getFullYear())</script>. Design: <a href="http://blacktie.co/">BlackTie.co</a></span>
  </div>
</footer>


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="../static/assets/js/classie.js"></script>
<script src="../static/assets/js/bootstrap.min.js"></script>
<script src="../static/assets/js/smoothscroll.js"></script>
<script src="../static/assets/js/main.js"></script>
</body>
</html>

<script>
function userInput(){
    var url = document.getElementById("userId").value;
    location.href = url;
    return false;
}
</script>
