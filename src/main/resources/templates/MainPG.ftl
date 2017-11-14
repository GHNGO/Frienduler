<html>

<head>
  <meta charset="UTF-8">
  <title>Frienduler</title>

</head>

<body data-spy="scroll" data-offset="0" data-target="#theMenu">
<!-- Menu -->
<nav class="menu" id="theMenu">
  <div class="menu-wrap">
    <h1 class="logo"><a href="index.html">Frienduler</a></h1>
    <i class="icon-remove menu-close"></i>
    <!-- TODO: Friends list? if we dont implement friends list this way then delete menu -->
  </div>

  <!-- Menu button -->
  <div id="menuToggle"><i class="icon-reorder"></i></div>
</nav>

<body>
<form>
  <p>
    <button type="button" onclick="location.href='./Frienduler/addfriend'">
      Add Friend
    </button>
  </p>
</form>
<section>
  <!--for demo wrap-->
  <h1>Your Events</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
      <tr>
        <th>Event Name</th>
        <th>Date</th>
        <th>Start Time</th>
        <th>End Time</th>
      </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
      <#--TODO: make freemarker work-->
<#--
      <#list events as event>
        <tr>
          <td>${event.name}</td>
          <td>${event.date}</td>
          <td>${event.startTime}</td>
          <td>${event.endTime}</td>
        </tr>
      </#list>
-->
      </tbody>
    </table>
  </div>
</section>


<style>

  h1{
    font-size: 30px;
    color: #fff;
    text-transform: uppercase;
    font-weight: 300;
    text-align: center;
    margin-bottom: 15px;
  }
  table{
    width:100%;
    table-layout: fixed;
  }
  .tbl-header{
    background-color: rgba(255,255,255,0.3);
  }
  .tbl-content{
    height:300px;
    overflow-x:auto;
    margin-top: 0px;
    border: 1px solid rgba(255,255,255,0.3);
    background-color: rgba(0,0,0,.6);
  }
  th{
    padding: 20px 15px;
    text-align: left;
    font-weight: 500;
    font-size: 12px;
    color: rgba(20,57,78,1);
    text-transform: uppercase;
  }
  td{
    padding: 15px;
    text-align: left;
    vertical-align:middle;
    font-weight: 300;
    font-size: 12px;
    color: #fff;
    border-bottom: solid 1px rgba(255,255,255,0.1);
  }


  /* demo styles */

  @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
  body{
    background: -webkit-linear-gradient(left, #25c481, #25b7c4);
    background: linear-gradient(to right, #25c481, #25b7c4);
    font-family: 'Roboto', sans-serif;
  }
  section{
    margin: 50px;
  }


  /* follow me template */
  .made-with-love {
    margin-top: 40px;
    padding: 10px;
    clear: left;
    text-align: center;
    font-size: 10px;
    font-family: arial;
    color: #fff;
  }
  .made-with-love i {
    font-style: normal;
    color: #F50057;
    font-size: 14px;
    position: relative;
    top: 2px;
  }
  .made-with-love a {
    color: #fff;
    text-decoration: none;
  }
  .made-with-love a:hover {
    text-decoration: underline;
  }


  /* for custom scrollbar for webkit browser*/

  ::-webkit-scrollbar {
    width: 6px;
  }
  ::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
  }
  ::-webkit-scrollbar-thumb {
    -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
  }

  /* MENU CONF*/

  .menu {
    position: fixed;
    right: -200px;
    width: 260px;
    height: 100%;
    top: 0;
    z-index: 10;
    text-align: left;
  }

  .menu.menu-open {
    right: 0px;
  }

  .menu-wrap {
    position: absolute;
    top: 0;
    left: 60px;
    background: #1a1a1a;
    width: 200px;
    height: 100%;
  }

  .menu h1.logo a {
    font-family: 'Oswald', sans-serif;
    font-size: 16px;
    font-weight: 700;
    letter-spacing: 0.15em;
    line-height: 40px;
    text-transform: uppercase;
    color: #ffffff;
    margin-top: 20px;
  }

  .menu h1.logo a:hover {
    color: #3cf8ca;
  }

  .menu img.logo {
    margin: 20px 0;
    max-width: 160px;
  }

  .menu a {
    margin-left: 20px;
    color: #808080;
    display: block;
    font-size: 12px;
    font-weight: 700;
    line-height: 40px;
    letter-spacing: 0.1em;
    text-transform: uppercase;
  }

  .menu a:hover {
    color: #ffffff;
  }

  .menu a:active {
    color: #ffffff;
  }

  .menu a > i {
    float: left;
    display: inline-block;
    vertical-align: middle;
    text-align: left;
    width: 25px;
    font-size: 14px;
    line-height: 40px;
    margin: 25px 2px;
  }

  .menu-close {
    cursor: pointer;
    display: block;
    position: absolute;
    font-size: 14px;
    color: #808080;
    width: 40px;
    height: 40px;
    line-height: 40px;
    top: 20px;
    right: 5px;
    -webkit-transition: all .1s ease-in-out;
    -moz-transition: all .1s ease-in-out;
    -ms-transition: all .1s ease-in-out;
    -o-transition: all .1s ease-in-out;
    transition: all .1s ease-in-out;
  }

  .menu-close:hover {
    color: #ffffff;
    -webkit-transition: all .1s ease-in-out;
    -moz-transition: all .1s ease-in-out;
    -ms-transition: all .1s ease-in-out;
    -o-transition: all .1s ease-in-out;
    transition: all .1s ease-in-out;
  }

  /* Push the body after clicking the menu button */
  .body-push {
    overflow-x: hidden;
    position: relative;
    left: 0;
  }

  .body-push-toright {
    left: 200px;
  }

  .body-push-toleft {
    left: -200px;
  }

  .menu,
  .body-push {
    -webkit-transition: all .3s ease;
    -moz-transition: all .3s ease;
    -ms-transition: all .3s ease;
    -o-transition: all .3s ease;
    transition: all .3s ease;
  }

  #menuToggle {
    position: absolute;
    top: 20px;
    left: 0;
    z-index: 11;
    display: block;
    text-align: center;
    font-size: 14px;
    color: #ffffff;
    width: 40px;
    height: 40px;
    line-height: 40px;
    cursor: pointer;
    background: rgba(0,0,0,0.25);
    -webkit-transition: all .1s ease-in-out;
    -moz-transition: all .1s ease-in-out;
    -ms-transition: all .1s ease-in-out;
    -o-transition: all .1s ease-in-out;
    transition: all .1s ease-in-out;
  }

  #menuToggle:hover {
    color: #ffffff;
    background: rgba(0,0,0,0.2);
    -webkit-transition: all .1s ease-in-out;
    -moz-transition: all .1s ease-in-out;
    -ms-transition: all .1s ease-in-out;
    -o-transition: all .1s ease-in-out;
    transition: all .1s ease-in-out;
  }
</style>

</body>
</html>
