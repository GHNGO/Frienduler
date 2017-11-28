
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <title>Manage ${userId}</title>

    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="/js/bootstrap.min.css">
    <link rel="stylesheet" href="/js/bootstrap-theme.css">
    <link rel="stylesheet" href="/js/fontAwesome.css">
    <link rel="stylesheet" href="/js/hero-slider.css">
    <link rel="stylesheet" href="/js/owl-carousel.css">
    <link rel="stylesheet" href="/js/datepicker.css">
    <link rel="stylesheet" href="/js/tooplate-style.css">
    <link rel="stylesheet" href="/js/sidenav.css">
    <link rel="stylesheet" href="/js/button.css">
<#--<link rel="stylesheet" href="/js/tealBody.css">-->

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">

    <script src="/js/modernizr-2.8.3-respond-1.4.2.min.js"></script>

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

    <script src="/js/manageUser.js"></script>
</head>

<body class="sexy-body">
<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
<#list friends as friend>
    <a>${friend.firstName} ${friend.lastName}</a>
</#list>
</div>

<section class="banners" id="top">
    <br>
    <span style="color:white;cursor:pointer;" onclick="openNav()"> <object align="right"><input type="button" class="buttoner" value="&#9776; Friends List"/></object></span>
    <div id = "main">
        <#--<h1 style="text-align:center;">Manage User: ${userId}</h1>-->
        <div class="container">
            <h1 style="text-align:center;">Manage User: ${userId}</h1>
            <#--<div class="row">-->
                <#--<div class="col-md-6 col-md-offset-3">-->

                    <section id="first-tab-group" class="tabgroup">
                        <div id="tab1">
                            <div class="submit-form">
                                <form id="form-submit" action="">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="First Name">First Name:</label>
                                        </div>

                                        <div class="col-md-6">
                                            <label for="Last Name">Last Name:</label>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <fieldset>
                                                <input id="firstName" type="text" class="form-control date" value="${userObject.firstName}" required>
                                            </fieldset>
                                        </div>

                                        <div class="col-md-6">
                                            <fieldset>
                                                <input id="lastName" type="text" class="form-control date" value="${userObject.lastName}" required>
                                            </fieldset>
                                        </div>

                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <fieldset>
                                                <button id="form-submit" class="btn" onclick="updateUser('${userId}')">Save Changes</button>
                                            </fieldset>
                                        </div>
                                    </div>

                                </form>

                            </div>

                            <div>
                            </div>
                            <object align="center"><a href="/Frienduler/user/${userId}"><input type="button" class="buttoner" value="Return to Home"/></a></object>
                        </div>
                    </section>
                </div>
            <#--</div>-->
        </div>
    <#--</div>-->
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
