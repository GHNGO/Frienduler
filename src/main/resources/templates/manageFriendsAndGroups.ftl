
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <!--

    -->
    <title>Event</title>

    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <link rel="stylesheet" href="/js/bootstrap.min.css">
    <link rel="stylesheet" href="/js/bootstrap-theme.min.css">
    <link rel="stylesheet" href="/js/fontAwesome.css">
    <link rel="stylesheet" href="/js/hero-slider.css">
    <link rel="stylesheet" href="/js/owl-carousel.css">
    <link rel="stylesheet" href="/js/datepicker.css">
    <link rel="stylesheet" href="/js/tooplate-style.css">
    <link rel="stylesheet" href="/js/sidenav.css">
    <link rel="stylesheet" href="/js/button.css">

    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">

    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

    <script src="/js/modernizr-2.8.3-respond-1.4.2.min.js"></script>
    <script src="/js/manageFriendsAndGroups.js"></script>
</head>

<body class="pritty-body">
<div id="mySidenav" class="sidenav">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
<#list friends as friend>
    <a>${friend.firstName} ${friend.lastName}</a>
</#list>
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
                            <form id="form-submit">
                                <div class="row">
                                    <label>Add friends:</label>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <fieldset>
                                            <select datatype="text" itemtype="text" id="input_friend_id">
                                            <#list usersNotFriends as user>
                                                <option value="${user.id}">${user.id}</option>
                                            </#list>
                                            </select>
                                        <#--<input type="text" class="form-control date" placeholder="Friend name..." required="" id="name" name="name">-->
                                        </fieldset>
                                    </div>
                                    </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <fieldset>
                                            <button type="submit" id="form-submit" class="btn" onclick="addFriend('${currentUser}')">Add Friend</button>
                                        </fieldset>
                                    </div>
                                </div>

                                <hr>
                                <div></div>
                                <div class="row">
                                    <label>Remove friends:</label>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <select datatype="text" itemtype="text" id="input_friend_id_remove">
                                        <#list friends as user>
                                            <option value="${user.id}">${user.id}</option>
                                        </#list>
                                        </select>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <fieldset>
                                            <button type="submit" id="form-submit" class="btn" onclick="removeFriend('${currentUser}')">Remove Friend</button>
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
                                    <label>Create a group:</label>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <fieldset>
                                            <input type="text" class="form-control date" id="add_group_input" placeholder="Group name...">
                                        </fieldset>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <fieldset>
                                            <button type="submit" id="form-submit" class="btn" onclick="addGroup()">Create group</button>
                                        </fieldset>
                                    </div>
                                </div>

                                <div class="row">
                                    <label for="from">Or add a friend to an existing group</label>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <fieldset>
                                            <select datatype="text" itemtype="text" id="group_select">
                                            <#list groups as group>
                                                <option value="${group.id}">${group.id}</option>
                                            </#list>
                                            </select>
                                        </fieldset>
                                    </div>
                                    <div class="col-md-6">
                                        <fieldset>
                                            <select datatype="text" itemtype="text" id="friend_id_add_group">
                                            <#list friends as user>
                                                <option value="${user.id}">${user.id}</option>
                                            </#list>

                                            </select>
                                        </fieldset>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <fieldset>
                                            <button type="submit" id="form-submit" class="btn" onclick="addFriendToGroup()">Add to group</button>
                                        </fieldset>
                                    </div>
                                    <div class="col-md-6">
                                        <fieldset>
                                            <button type="submit" id="form-submit" class="btn" onclick="addSelfToGroup('${currentUser}')">Add Yourself to the Group</button>
                                        </fieldset>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Or remove a user from a group</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <fieldset>
                                            <select datatype="text" itemtype="text" id="group_select_remove">
                                            <#list groups as group>
                                                <option value="${group.id}">${group.id}</option>
                                            </#list>
                                            </select>
                                        </fieldset>
                                    </div>
                                    <div class="col-md-6">
                                        <fieldset>
                                            <select datatype="text" itemtype="text" id="friend_id_remove_from_group">
                                            <#list users as user>
                                                <option value="${user.id}">${user.id}</option>
                                            </#list>
                                            </select>
                                        </fieldset>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <fieldset>
                                            <button type="submit" id="linkManageGroup" class="btn" onclick="removeFromGroup()">Remove from Group</button>
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
        <object align="center"><a href="/Frienduler/user/${currentUser}/"><input type="button" class="buttoner" value="Return to Home"/></a></object>
    </div>
</section>
<script>
    function openNav() {
        document.getElementById("mySidenav").style.width = "10%";
        document.getElementById("main").style.marginRight = "250px";
    }

    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
        document.getElementById("main").style.marginRight = "0";
    }
</script>
</body>
</html>
