<!DOCTYPE html>
<html>    
    <head>
        <!--[if lt IE 9]>
        <script>
          var e = ("article,aside,figcaption,figure,footer,header,hgroup,nav,section,time").split(',');
          for (var i = 0; i < e.lenght; i++){
            document.createElement(e[i]);
          }
        </script>
        <![endif]-->
        <meta charset="cp1251">
        <title>Lets</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap.css" media="all" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-theme.css" media="all" />
        <style>
            body {
                background:#f1f1f1;
                overflow-x: hidden;
            }
            .DialogName{
                cursor: pointer;
                color:#666666;
                font-size: 1.3em;  
                margin: 6px;
                 
            }
            .DialogName:hover{
                color:#888888;  
            }
            .DialogName:active{
                color:#aaaaaa;  
            }
            .Header{
                height: 32px;
                color:#666666;
                font-size: 1.3em;
            }
            div.DialogTime {
              display: inline-block;
              position: relative;
              bottom: -41px;
              left: 10px;
              font-size: 0.8em;
              color: #999999; 
            }
            i.DialogTime {
              margin-left: 10px;
              margin-right: 10px;
              font-size: 0.7em;
              color: #999999; 
            }
            i.DialogTimeUnread {
              margin-left: 10px;
              margin-right: 10px;
              font-size: 0.7em;
              color: #777777; 
            }
            .glyphicon {
                cursor: pointer;
            }
            .AnimateGlyph {
                margin: 5px 5px 5px 5px;
                color: #cccccc;
            }
            .AnimateGlyph:hover {
                color: #aaaaaa;
            }
            .AnimateGlyph:active {
                color: #eeeeee;
            }
            .unreadMessage {
              min-height: 30px;
              border-radius: 10px;
              border: 1px solid #ffffff;
              background: #cccccc;  
              color: #777777;
              font-size: 14px;
            }
            div.unreadMessage {
                margin-bottom: 5px;
                padding:7px;
                border: 1px solid #cccccc;
                border-radius: 8px;
            }
            .notUnreadMessage {
              min-height: 30px;
              border-radius: 10px;
              border: 1px solid #ffffff;
              background: #f8f8f8;  
              color: #777777;
              font-size: 14px;
            }
            div.notUnreadMessage {
                margin-bottom: 5px;
                padding:7px;
                border-radius: 8px;
            }
            textarea.noscroll{
                min-height: 54px;
               width: 841px;
               font-size: 13px;
               color: #999999;
               overflow: hidden;
               padding: 5px;
            }
            .hiddenDiv{
                min-height: 54px;
                display: none;
                white-space: pre-wrap;
                width: 841px;
                font-size: 13px;
                padding: 5px;
                word-wrap: break-word;
            }
            .Home-li-dropdown{
                display: none;
                position:absolute; 
                top:0px;
                z-index: 1000;  
            }
            .Home-li-dropdown-menu{
                background: #fcfcfc; 
                border: 1px solid #cccccc; 
                border-radius: 8px;
                font-size: 12px;
                color: #757575;
            }
            .Home-li-dropdown-menu > div{
                margin:0px 10px 0px 10px;
                cursor: pointer;
            }
            
            .PhotoFrame{
                display:inline-block; 
                vertical-align: middle;
                border: 1px solid #cccccc; 
                margin:2px; 
                padding: 1px;
                height:205px; 
                width:205px; 
                background: #222222;
                font-size:0.9em; 
                color:white;   
                cursor: pointer;
            }
            
            .PhotoFrame > span{
                display: table-cell;
                height: 201px;
                vertical-align: middle;
                text-align: center;
                width: 201px;

            }
            
            .PhotoFrame > span > img{
                display: inline;
                max-height: 201px;
                max-width: 201px;
            }
            
            .UserGlyphicons{
                opacity: 0.8;
            }
            #Search-li{
                margin-right:15px;
            }
            #Search-li > input{
                margin: 10px;
                height:30px;
                color: #888888;
            }
            #Search-li-dropdown{
                position: absolute;
                right: -30px;
                z-index: 1000;
                width: 300px;
            }
            #Search-li-dropdown > ul{ 
                width: 300px;
            }
            .Search-circle{
                position: relative;
                left: 46%;
                color:#aaaaaa;
            }
            #ContainerSearch{
                display: none;
                position: absolute;
                top:60px;
                left: 10px;
                background: #fefefe;
                border: 1px solid #cecece; 
                border-radius: 8px;
                padding: 10px;
                opacity:0.4;
                width: 193px;
            }
        </style>
       
 
        <%  
            Cookie[] cookies = request.getCookies();
            String user = "god";
            if (cookies != null){
              for (Cookie cookie : cookies){
                 if (cookie.getName().equals("username"))
                      {user = cookie.getValue(); break;}
              }
            }
        %>
        
       
    </head>
    <body>
        <script src="jquery-2.1.1.min.js"></script>
        <script src="bootstrap.js"></script>
        
       
        
        <nav class="navbar navbar-default" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Brand</a>
                </div>
                <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
                    <ul id='MainNavBarUl' class="nav navbar-nav">
                        
                        <%  if (!user.equals("")){ %>
                        
                        <li id='Search-li'><input type="text" class="form-control" placeholder="Search"><div id="Search-li-dropdown" style="display:none;"><ul class="list-group"></ul></div></li>
                        <li id='Users-li' onclick='navbarButton(this)'><a href="#" >Users</a></li>
                        <li id='Dialogs-li' onclick='navbarButton(this)'><a href="#">Dialogs <span id= 'NumberOfUnreadDialogs' class='badge'></span></a></li>
                        <li id='Home-li'><a href="#" onclick='displayHomeDropdown()'></a>
                          <div class='Home-li-dropdown'>
                              <div style='height:51px; opacity: 0'></div>
                            <div class='Home-li-dropdown-menu' >
                                    <div onclick='getUser(this)'>Home</div>
                                    <hr style='margin:0px'>
                                    <div>Adverticement</div>
                                    <hr style='margin:0px'>
                                    <div onclick='deleteCookie()'>Exit</div>
                            </div>
                          </div>    
                        </li>
                        
                        <% } else { %>
                        
                        <li id='Signup-li' onclick='navbarButton(this)'><a href="#">Sign up</a></li>
                        <li id='Login-li' onclick='navbarButton(this)'><a href="#" >Log in</a></li>
                        
                         <% } %>
                    </ul>
                </div>
            </div>
         </nav>  
                     
          
        <div id="Container" class="container-fluid" style='display: block'>
            <ul id='ContainerList' class="list-group"></ul>
            <div id="ContainerSearch"></div>
        </div> 
                        
         <script>
             
            var EnlargeElementOpacity = null;
            var EnlargeElementOpacityTimer = null;
            var ReduceElementOpacity = null;
            var ReduceElementOpacityTimer = null;
            var SearchBlur = false;
            var SearchMouseOut = false;
            var NoMoreElements = false;
            
            var timerNumberDialogs;
            if (!!getCookie('username'))  {   
                document.getElementById('Home-li').firstChild.innerHTML = getCookie('username')+"<div class='PhotoFrame' style='position:absolute; left:"+(28+getCookie('username').split('').length*6.2)+"px; bottom:10px; width:30px; height:30px; border:0px; margin:0px 0px 0px 0px;'><span style='width:28px; height:28px;'><img id='HomeLiUserImg' src='images/"+getCookie('useravatar')+"low.jpg' alt='"+getCookie('useravatar')+"' style='max-width:28px; max-height:28px;' style='cursor: pointer;'/></span></div>";
                        $('.Home-li-dropdown-menu > div').get(0).id = getCookie('username');
                 timerNumberDialogs = setInterval(NumberOfUnreadDialogs,4000);
                 SetSearchActions();
             }
             var timerLastMessageId;
            
            function SetSearchActions(){
            $('#Search-li > input').bind('focus',function(){ SearchBlur = false; getSearchContent();});
            $('#Search-li > input').bind('keyup',function(){ $('#Search-li-dropdown > ul').get(0).innerHTML= ""; getSearchContent();});
            $('#Search-li > input').bind('blur',function(){
                  SearchBlur = true;
                  if ((SearchBlur)&&(SearchMouseOut)){
                      $('#Search-li-dropdown > ul').get(0).innerHTML= "";
                      document.getElementById('Search-li-dropdown').style.display = 'none';
                      document.getElementById('Search-li-dropdown').style.opacity = 0;
                     ReduceElementOpacity = true;}
            });
            $('#Search-li-dropdown').bind('mouseover',handleMouseEnter(function(){SearchMouseOut = false;}));
            $('#Search-li-dropdown').bind('mouseout',handleMouseLeave(function(){
                  SearchMouseOut = true;
                  if ((SearchBlur)&&(SearchMouseOut)){
                      $('#Search-li-dropdown > ul').get(0).innerHTML= "";
                      document.getElementById('Search-li-dropdown').style.display = 'none';
                      document.getElementById('Search-li-dropdown').style.opacity = 0;
                     ReduceElementOpacity = true;}}));
            };
            
             function getCookie(name){
                var cookie = ' ' + document.cookie;
                var search = ' ' + name + '=';
                var setStr = null;
                var offset = 0;
                var end = 0;
                if (cookie.length > 0){
                    offset = cookie.indexOf(search);
                    if (offset !== -1){
                        offset += search.length;
                        end = cookie.indexOf(';',offset);
                        if (end === -1) 
                            end = cookie.length;
                        setStr = unescape(cookie.substring(offset,end));
                    }
                }
                return (setStr);
            };
            
            function setCookie(name, value, options) {
                 options = options || {};
                 var expires = options.expires;
                 if (typeof expires === "number" && expires) {
                 var d = new Date();
                 d.setTime(d.getTime() + expires*1000);
                 expires = options.expires = d;
                }
                if (expires && expires.toUTCString) {
                  options.expires = expires.toUTCString();
                }
                value = encodeURIComponent(value);
                var updatedCookie = name + "=" + value;
                for(var propName in options) {
                     updatedCookie += "; " + propName;
                     var propValue = options[propName];   
                     if (propValue !== true) {
                       updatedCookie += "=" + propValue;
                     }
                }
                document.cookie = updatedCookie;
            }
             
            function deleteCookie(){
                setCookie("username", "", { expires: -1 });
                setCookie("useravatar", "", { expires: -1 });
                clearTimeout(timerNumberDialogs);
                document.getElementById('ContainerSearch').innerHTML = "";
                document.getElementById('ContainerSearch').style.display = "none";
                document.getElementById('ContainerList').innerHTML = "";
                document.getElementById('MainNavBarUl').innerHTML = "<li id='Signup-li' onclick='navbarButton(this)'><a href='#'>Sign up</a></li><li id='Login-li' onclick='navbarButton(this)'><a href='#' >Log in</a></li>";
            }
            
            function getSearchContent(next){
                next = next || 'true';
                var parent = $('#Search-li-dropdown > ul').get(0);
                var request = cleanText($('#Search-li > input').val());
                var xhr = new XMLHttpRequest();
                xhr.open('GET','fastsearch?search='+request,true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var response = JSON.parse(xhr.responseText);
                    var header = (+response.length === 0) ? 'Nothing' : 'Users:';
                    var content = "<li class='list-group-item Header' style='background:#dddddd; height:32px; padding:0px 0px 0px 10px'>"+header+"</li>"; 
                    if (response.noMoreBefore === 'false') content += "<li class='list-group-item' style='background:#eeeeee; height:20px; padding:0px 0px 0px 10px'><span class='glyphicon glyphicon-circle-arrow-up  Search-circle'></span></li>";  
                    for (var i = 0; i < response.length; i++){
                        content += "<li class='list-group-item userBySearch' style='background:#fafafa; height:32px; padding:0px 0px 0px 10px'><div class='PhotoFrame' style='width:30px; height:30px; border:0px; float:left; margin:0px 5px 0px 0px;'><span style='width:28px; height:28px;'><img src='images/"+response[i].avatar+"low.jpg' alt='"+response[i].login+"' style='max-width:28px; max-height:28px;' onclick='getUserFromSearch(this)'></span></div><div class='DialogName' onclick='getUserFromSearch(this)' style='display:table-cell; height:30px; vertical-align:middle;'> "+response[i].login+"</div></li>"; 
                    }
                    if (response.noMoreAfter === 'false') content += "<li class='list-group-item' style='background:#eeeeee; height:20px; padding:0px 0px 0px 10px'><span class='glyphicon glyphicon-circle-arrow-down Search-circle'></span></li>"; 
                    $('#Search-li-dropdown > ul').get(0).innerHTML = content;
                    if (response.noMoreBefore === 'false') $('#Search-li-dropdown .list-group-item').get(1).onclick = function(){
                         getSearchContent('false');
                    };
                    if (response.noMoreAfter === 'false') $('#Search-li-dropdown .list-group-item').last().bind('click',function(){
                         getSearchContent('true');
                    });
                    EnlargeElementOpacity = true;
                    if (document.getElementById('Search-li-dropdown').style.display === 'none')
                            enlargeElementOpacity(document.getElementById('Search-li-dropdown'), 0.9);
                    
                };
                var Login = '';
                if ($('#Search-li-dropdown > ul').get(0).innerHTML !== "")
                    Login = (next === 'true')? $('.userBySearch img').last().attr('alt') : $('.userBySearch img').first().attr('alt');
           
                xhr.setRequestHeader("Login",Login);
                xhr.setRequestHeader("next",next);
                xhr.send('');
            }

             function displayHomeDropdown(){
                $('.Home-li-dropdown').first().css('opacity','0');
                $('.Home-li-dropdown').get(0).style.display = 'block';
                document.getElementsByClassName('Home-li-dropdown')[0].onmouseout=handleMouseLeave(function(){
                                                    ReduceElementOpacity = true;
                                                    reduceElementOpacity($('.Home-li-dropdown').get(0),true);
                                          });
                document.getElementsByClassName('Home-li-dropdown')[0].onmouseover=handleMouseEnter(function(){
                                                    EnlargeElementOpacity = true;
                                                    enlargeElementOpacity($('.Home-li-dropdown').get(0),1);
                                          });
             };
             
            function enlargeElementOpacity(elem, max, display){
                           var display = display || "block";
                           ReduceElementOpacity = false;
                           clearTimeout(ReduceElementOpacityTimer);
                           if (!EnlargeElementOpacity) return;
                           if (elem.length !== undefined)
                           {   for (var i = 0; i< elem.length; i++)
                                  if(elem[i].style.display === 'none') elem[i].style.display = display;
                               var opacity = +elem[0].style.opacity;
                               opacity = (opacity * 10 + 1)/10;
                               for (var i = 0; i< elem.length; i++)
                                  elem[i].style.opacity = opacity;
                           } else{ 
                              if(elem.style.display === 'none') elem.style.display = display;
                              var opacity = +elem.style.opacity;
                              opacity = (opacity * 10 + 1)/10;
                              elem.style.opacity = opacity;
                       }
                           if (opacity < max) EnlargeElementOpacityTimer = setTimeout(function(){enlargeElementOpacity(elem, max);},30); 
             };

            function reduceElementOpacity(elem, useTimeout, min){
                           EnlargeElementOpacity = false;
                           clearTimeout(EnlargeElementOpacityTimer);
                           if (!ReduceElementOpacity) return;
                           min = min || 0;
                           if (elem.length !== undefined)
                           {   var opacity = +elem[0].style.opacity;
                               if (opacity * 10 > min*10+1) opacity = (opacity * 10 - 1)/10;
                               for (var i = 0; i< elem.length; i++)
                                  elem[i].style.opacity = opacity;
                           } else{ 
                               var opacity = +elem.style.opacity;
                               if (opacity * 10 > min*10+1) opacity = (opacity * 10 - 1)/10;
                               elem.style.opacity = opacity;
                           }
                           if ((opacity * 10 > min*10+1)&&(useTimeout))  ReduceElementOpacityTimer = setTimeout(function(){reduceElementOpacity(elem,useTimeout,min);},30); 
                           else { var time =  (useTimeout)? 0 : 50;
                             setTimeout(function(){
                               if (elem.length !== undefined)
                               {for (var i = 0; i< elem.length; i++)
                                   {elem[i].style.opacity = min;
                                   if (min===0) elem[i].style.display = 'none';}}
                               else {
                                   elem.style.opacity = min; 
                                   if (min===0) elem.style.display = 'none';
                               };
                             },time);
                           };
            };
             
             function handleMouseLeave(handler){
                return function(e){
                    e = e || event;
                    var toElement = e.relatedTarget || e.toElement;
                    while (toElement && toElement !== this) {
                        toElement = toElement.parentNode;
                    }
                    if (toElement === this){
                        return;
                    }
                    return handler.call(this, e);
                };
             };
             
            function handleMouseEnter(handler){
                return function(e){
                    e = e || event;
                    var toElement = e.relatedTarget || e.srcElement;
                    while (toElement && toElement !== this) {
                        toElement = toElement.parentNode;
                    }
                    
                    if (toElement === this){
                        return;
                    }
                    return handler.call(this, e);
                };
             };
             
             function NumberOfUnreadDialogs(){
                var xhr = new XMLHttpRequest();
                xhr.open('GET','numberofdialogs',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    console.log(xhr.responseText);
                    if (+xhr.responseText > 0) document.getElementById('NumberOfUnreadDialogs').innerHTML = xhr.responseText;
                    else document.getElementById('NumberOfUnreadDialogs').innerHTML = "";
                    };
                xhr.send('');
             };
             
             function checkLastDialogsMessageId(){
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getlastdialogsmessageid',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    
                    var tableId = $('.list-group-item > table').first().attr('id');
                    if (tableId !== ('messageId_'+xhr.responseText)) {
                        clearTimeout(timerLastMessageId);
                        printDialogs();}
                };
                xhr.send('');
            };
            
             function checkLastMessageId(){
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getlastmessageid',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var response = JSON.parse(xhr.responseText);
                    var tableId = $('#DialogTable table').last().attr('id');
                    if ((response.unread !== undefined) && (response.unread === "false")){
                         ChangeDialogStyles();
                    }
                    if (tableId === undefined) tableId = 'messageId_-1';
                    if ((tableId !== ('messageId_'+response.id))) {
                                    clearTimeout(timerLastMessageId);
                                    getNewMessages();}
                    
                };
                xhr.setRequestHeader("other",$('.DialogName').first().html());
                xhr.send('');
            };
             
             
             
             function navbarButton(elem){
             window.onscroll = function(){};
             clearTimeout(timerLastMessageId);
             cleanNavbar();
             SetToZeroContainerSearch();
             elem.className = "active";
             switch(elem.id){ 
             case 'Users-li': printUsers(); break;
             case 'Dialogs-li': printDialogs(); break;
             case 'Signup-li': signUpForm(); break;
             case 'Login-li': logInForm(); break;    
              }
            };
            
            function signUpForm(){
                var container = document.getElementById('ContainerSearch');
                container.onmouseout=function(){};
                container.onmouseover=function(){};
                container.style.opacity = 0.8;
                container.style.width = '260px';
                container.style.display = 'block';
                var content = "<div class='Header'>Sign Up</div>";
                content += SearchValueToTable("Username","<input id='NewLoginInput' type='text' class='form-control' maxlength='20' style='width:100%; height:25px;'>", 80 );
                content += SearchValueToTable("Password","<input id='PasswordInput' type='password' class='form-control' maxlength='20' style='width:100%; height:25px;'>", 80 );
                content += SearchValueToTable("City","<input id='CityInput' type='text' class='form-control' maxlength='20' style='width:100%; height:25px;'>", 80 );
                content += SearchValueToTable('<input type="radio" name="SexInput" value="MALE">Male','<input type="radio" name="SexInput" value="FEMALE">Female', 80 );
                var DayContainer = new Object();
                DayContainer.id = "DayInput";
                DayContainer.name = "Day";
                DayContainer.length = 31;
                for (var i = 0; i < DayContainer.length; i++){
                    DayContainer[i] = i+1;
                };
                var MonthContainer = new Object();
                MonthContainer.id = "MonthInput";
                MonthContainer.name = "Month";
                MonthContainer.length = 12;
                for (var i = 0; i < MonthContainer.length; i++){
                    MonthContainer[i] = new Object();
                    MonthContainer[i].value = i;
                    MonthContainer[i].select = getMonth(i);
                };
                var YearContainer = new Object();
                YearContainer.id = "YearInput";
                YearContainer.name = "Year";
                YearContainer.length = 100;
                for (var i = 0; i < YearContainer.length; i++){
                    YearContainer[i] = new Date().getFullYear() - 10 - i;
                };
                content += SearchValueToTable("Birthday", newSelectForm(DayContainer)+newSelectForm(MonthContainer)+newSelectForm(YearContainer), 80);
                content += "<div class='Header' style='margin: 5px 3px 3px 3px;'>About You<span id='ShowSignUpTextarea' class='glyphicon glyphicon-plus AnimateGlyph' style='font-size:0.9em'></span></div>";
                content += "<textarea id='TextArea' class='noscroll' name='text' style='display:none; width:237px'></textarea>";
                content += "<button id='SignupButton' style='width:100%; height:25px; margin-top: 2px; color:dddddd;'>Sign Up</button>";
                container.innerHTML = content;
                var Username = document.getElementById('NewLoginInput');
                var Password = document.getElementById('PasswordInput');
                var City = document.getElementById('CityInput');
                var Year =  document.getElementById("YearInput");
                var Month =  document.getElementById("MonthInput");
                var Day =  document.getElementById("DayInput");
                var Button = document.getElementById('SignupButton');
                var textarea = document.getElementById('TextArea');
                var radios = document.getElementsByTagName('SexInput');
                var Sex = "BOTH";
                for (var i = 0; i < radios.length; i++) {
                      if (radios[i].type === 'radio' && radios[i].checked && radios[i].name === 'radioName') { 
                                Sex = radios[i].value;       
                      }
                }
                document.getElementById("ShowSignUpTextarea").onclick = function(){
                       
                       if (textarea.style.display === "none"){
                           textarea.style.display = "block";
                           this.className = "glyphicon glyphicon-minus AnimateGlyph";
                       } else {
                           textarea.style.display = "none";
                           this.className = "glyphicon glyphicon-plus AnimateGlyph";
                       };
                };
                
                Year.onchange = ChangeCountOfDays;
                Month.onchange = ChangeCountOfDays;
                Username.onkeyup = CheckNewLogin;
                Username.onblur = function(){
                    if (this.style.borderColor === "green") this.style.borderColor = "";
                };
                Password.onkeyup = CheckPassword;
                Password.onblur = function(){
                    if (this.style.borderColor === "green") this.style.borderColor = "";
                };
                Button.onclick = function(){
                    if ((Password.value.split('').length > 3) && (Username.value !== "") && (Username.style.borderColor !== "red") && (Password.style.borderColor !== "red")){
                        this.innerHTML = "Wait...";
                        this.onclick = function(){};
                         signUp(Username.value, Password.value, City.value, Sex, Day.value, Month.value, Year.value, textarea.value);
                    } else {
                        this.innerHTML = "Wrong Input";
                        if (Username.value === "") Username.style.borderColor = "red";
                        if (Password.value.split('').length <= 3) Password.style.borderColor = "red";
                    }
                };
                ChangeTextAreaSize();
            };
            
            function ChangeCountOfDays(){
                var year = $("#YearInput").val();
                var month = $("#MonthInput").val();
                var DayInput = $("#DayInput");
                var day = DayInput.val();
                var DaysCount = 33 - new Date(+year, +month, 33).getDate();
                var content = "";
                for (var i = 1; i <= DaysCount; i++){
                     content += "<option value=" + i + ">" + i + "</option>";
                }
                DayInput.html(content);
                DayInput.val((DaysCount < day) ? DaysCount : day);
                
            }
            
            function CheckNewLogin(){
                var input =$('#NewLoginInput');
                var login = input.val();
                if(!!login){
                       var loginChars = login.split('');
                       if (!isNaN(loginChars[0])) {
                           input.css("border-color", "red");
                           return;
                       }
                            if (!/^[0-9a-z]+$/i.test(login)) {
                                  input.css("border-color", "red");
                                  return;
                            }
                       var xhr = new XMLHttpRequest();
                       xhr.open('GET','checkloginunique?username='+login,true);
                       xhr.onreadystatechange = function() {
                          if (this.readyState !== 4) return;
                          var unique = (xhr.responseText === "true");
                          input.css("border-color", (unique) ? "green" : "red");
                       };
                       xhr.send('');
                } else{
                    input.css("border-color", "");
                }
            }
            function CheckLogin(){
                var input =$('#LoginInput');
                var login = input.val();
                if(!!login){
                       var loginChars = login.split('');
                       if (!isNaN(loginChars[0])) {
                           input.css("border-color", "red");
                           return;
                       }
                       if (!/^[0-9a-z]+$/i.test(login)) {
                                  input.css("border-color", "red");
                                  return;
                       }
                       input.css("border-color", "");
                } else{
                    input.css("border-color", "");
                }
            }
            
            function CheckPassword(){
                var input = $('#PasswordInput');
                var password = input.val();
                if (!!password){
                          input.css("border-color", (/^[0-9a-z]+$/i.test(password)) ? "green" : "red");
                } else{
                    input.css("border-color", "");
                }
            }
            
            function signUp(Username, Password, City, Sex, Day, Month, Year, About){
                City = cleanText(City.replace(/(^\s+|\s+$)/g,''));
                About = cleanText(About);
                var xhr = new XMLHttpRequest();
                xhr.open('POST','signup?city='+City+'&about'+About,true);
                xhr.onreadystatechange = function() {
                    if (this.readyState !== 4) return;
                    var input = (xhr.responseText === "true");
                    if (input) {
                        var navbar =  document.getElementById('MainNavBarUl');
                        navbar.innerHTML = "<li id='Search-li'><input type='text' class='form-control' placeholder='Search'><div id='Search-li-dropdown' style='display:none;'><ul class='list-group'></ul></div></li>"+
                        "<li id='Advertisement-li' onclick='NumberOfUnreadDialogs()'><a href='#'>Advertisement</a></li>"+
                        "<li id='Users-li' onclick='navbarButton(this)'><a href='#' >Users</a></li>"+
                        "<li id='Dialogs-li' onclick='navbarButton(this)'><a href='#'>Dialogs <span id= 'NumberOfUnreadDialogs' class='badge'></span></a></li>"+
                        "<li id='Home-li'><a href='#' onclick='displayHomeDropdown()'></a>"+
                          "<div class='Home-li-dropdown'>"+
                             " <div style='height:51px; opacity: 0'></div>"+
                            "<div class='Home-li-dropdown-menu' >"+
                                    "<div onclick='getUser(this)'>Home</div>"+
                                    "<hr style='margin:0px'>"+
                                    "<div>Adverticement</div>"+
                                    "<hr style='margin:0px'>"+
                                    "<div onclick='deleteCookie()'>Exit</div>"+
                            "</div>"+
                          "</div>"  +  
                        "</li>";
                        var container = document.getElementById('ContainerSearch');
                        container.style.display = 'none';
                        container.style.opacity = 0.8;
                        container.style.width = '';
                        document.getElementById('Home-li').firstChild.innerHTML = getCookie('username')+"<div class='PhotoFrame' style='position:absolute; left:"+(28+getCookie('username').split('').length*6.2)+"px; bottom:10px; width:30px; height:30px; border:0px; margin:0px 0px 0px 0px;'><span style='width:28px; height:28px;'><img id='HomeLiUserImg' src='images/"+getCookie('useravatar')+"low.jpg' alt='"+getCookie('useravatar')+"' style='max-width:28px; max-height:28px;' style='cursor: pointer;'/></span></div>";
                        $('.Home-li-dropdown-menu > div').get(0).id = getCookie('username');
                        timerNumberDialogs = setInterval(NumberOfUnreadDialogs,4000);
                        SetSearchActions();
                        }
                    else 
                    { 
                        if (document.getElementById('SignupButton')) {
                            var Username = document.getElementById('NewLoginInput');
                            var Password = document.getElementById('PasswordInput');
                            var Button = document.getElementById('SignupButton');
                            Button.onclick = function(){
                                  if ((Username.value !== "") && (Password.value !== "") && (Username.style.borderColor !== "red") && (Password.style.borderColor !== "red")){
                                   this.innerHTML = "Wait...";
                                   this.onclick = function(){};
                                   logIn(Username.value, Password.value);
                                  } else {
                                     this.innerHTML = "Wrong Input";
                                    if (Username.value === "") Username.style.borderColor = "red";
                                    if (Password.value === "") Password.style.borderColor = "red";
                                  }
                             };
                            Button.innerHTML = "Problems with registration";
                        };
                    };
                };
                xhr.setRequestHeader("username", Username);
                xhr.setRequestHeader("password", Password);
                xhr.setRequestHeader("day", Day);
                xhr.setRequestHeader("month", Month);
                xhr.setRequestHeader("year", Year);
                xhr.setRequestHeader("sex", Sex);
                xhr.send('');
            };
            
            function logInForm(){
                var container = document.getElementById('ContainerSearch');
                container.onmouseout=function(){};
                container.onmouseover=function(){};
                container.style.opacity = 0.8;
                container.style.width = '';
                container.style.display = 'block';
                var content = "<div class='Header'>Log In</div>";
                content += SearchValueToTable("Username","<input id='LoginInput' type='text' class='form-control' maxlength='20' style='width:100%; height:25px;'>", 80 );
                content += SearchValueToTable("Password","<input id='PasswordInput' type='password' class='form-control' maxlength='20' style='width:100%; height:25px;'>", 80 );
                content += "<button id='LoginButton' style='width:100%; height:25px; margin-top: 2px; color:dddddd;'>Log In</button>";
                container.innerHTML = content;
                var Username = document.getElementById('LoginInput');
                var Password = document.getElementById('PasswordInput');
                var Button = document.getElementById('LoginButton');
                Button.onclick = function(){
                    if ((Username.value !== "") && (Password.value !== "") && (Username.style.borderColor !== "red") && (Password.style.borderColor !== "red")){
                        this.innerHTML = "Wait...";
                        this.onclick = function(){};
                         logIn(Username.value, Password.value);
                    } else {
                        this.innerHTML = "Wrong Input";
                        if (Username.value === "") Username.style.borderColor = "red";
                        if (Password.value === "") Password.style.borderColor = "red";
                    }
                };
                Password.onkeyup = CheckPassword;
                Password.onblur = function(){
                    if (this.style.borderColor === "green") this.style.borderColor = "";
                };
                Username.onkeyup = CheckLogin;
                Username.onblur = function(){
                    if (this.style.borderColor === "green") this.style.borderColor = "";
                };
            };
            
            function logIn(Username, Password){
                Username = Username.replace(/\s+/g, '');
                Password = Password.replace(/\s+/g, '');
                var xhr = new XMLHttpRequest();
                xhr.open('GET','login?username='+Username + '&password='+Password,true);
                xhr.onreadystatechange = function() {
                    if (this.readyState !== 4) return;
                    var input = (xhr.responseText === "true");
                    if (input) {
                        var navbar =  document.getElementById('MainNavBarUl');
                        navbar.innerHTML = "<li id='Search-li'><input type='text' class='form-control' placeholder='Search'><div id='Search-li-dropdown' style='display:none;'><ul class='list-group'></ul></div></li>"+
                        "<li id='Advertisement-li' onclick='NumberOfUnreadDialogs()'><a href='#'>Advertisement</a></li>"+
                        "<li id='Users-li' onclick='navbarButton(this)'><a href='#' >Users</a></li>"+
                        "<li id='Dialogs-li' onclick='navbarButton(this)'><a href='#'>Dialogs <span id= 'NumberOfUnreadDialogs' class='badge'></span></a></li>"+
                        "<li id='Home-li'><a href='#' onclick='displayHomeDropdown()'></a>"+
                          "<div class='Home-li-dropdown'>"+
                             " <div style='height:51px; opacity: 0'></div>"+
                            "<div class='Home-li-dropdown-menu' >"+
                                    "<div onclick='getUser(this)'>Home</div>"+
                                    "<hr style='margin:0px'>"+
                                    "<div>Adverticement</div>"+
                                    "<hr style='margin:0px'>"+
                                    "<div onclick='deleteCookie()'>Exit</div>"+
                            "</div>"+
                          "</div>"  +  
                        "</li>";
                        var container = document.getElementById('ContainerSearch');
                        container.style.display = 'none';
                        document.getElementById('Home-li').firstChild.innerHTML = getCookie('username')+"<div class='PhotoFrame' style='position:absolute; left:"+(28+getCookie('username').split('').length*6.2)+"px; bottom:10px; width:30px; height:30px; border:0px; margin:0px 0px 0px 0px;'><span style='width:28px; height:28px;'><img id='HomeLiUserImg' src='images/"+getCookie('useravatar')+"low.jpg' alt='"+getCookie('useravatar')+"' style='max-width:28px; max-height:28px;' style='cursor: pointer;'/></span></div>";
                        $('.Home-li-dropdown-menu > div').get(0).id = getCookie('username');
                        timerNumberDialogs = setInterval(NumberOfUnreadDialogs,4000);
                        SetSearchActions();
                        }
                    else 
                    {
                        if (document.getElementById('LoginButton')) {
                            var Username = document.getElementById('LoginInput');
                            var Password = document.getElementById('PasswordInput');
                            var Button = document.getElementById('LoginButton');
                            Button.onclick = function(){
                                  if ((Username.value !== "") && (Password.value !== "") && (Username.style.borderColor !== "red") && (Password.style.borderColor !== "red")){
                                   this.innerHTML = "Wait...";
                                   this.onclick = function(){};
                                   logIn(Username.value, Password.value);
                                  } else {
                                     this.innerHTML = "Wrong Input";
                                    if (Username.value === "") Username.style.borderColor = "red";
                                    if (Password.value === "") Password.style.borderColor = "red";
                                  }
                             };
                            Button.innerHTML = "Wrong combination";
                        };
                    };
                };
                xhr.send('');
            }
            
            function setContainerSearchContent(){
                var container = document.getElementById('ContainerSearch');
                var content = "<input id='UsersSearchInput' type='text' class='form-control' placeholder='Search' maxlength='20' style='width:100%; height:25px;'></input>";
                content += SearchValueToTable("Sex", newSelectForm({'id':'SexInput','name':'Sex','length':'3', '0':'BOTH', '1':"MALE", '2':"FEMALE"}));
                content += SearchValueToTable("City", "<input id='CityInput' type='text' class='form-control' placeholder='City' maxlength='20' style='width:100%; height:25px;'>");;  
                content += SearchValueToTable("Age", "<input id='AgeFromInput' type='text' class='form-control' pattern='^[ 0-9]' placeholder='from' maxlength='3' style='display:inline; width:48%; height:26px; margin:0px 4px 0px 0px; padding:0px;'><input id='AgeToInput' type='text' class='form-control' pattern='^[ 0-9]' placeholder='to' maxlength='3' style='display:inline; width:48%; height:26px;padding:0px;'>");;
                content += "<button id='ContainerSearchButton' style='width:100%; height:25px; margin-top: 2px; color:dddddd;'>Find</button>";
                container.innerHTML = content;
                $('#ContainerSearch select').each(function(){
                            this.onchange = function(){
                                 setTimeout(function(){ 
                                  container.style.opacity = 0.8;
                                  ReduceElementOpacity = false;
                                  ElargeElementOpacity = true;} , 100);
                            };
                });
                    
            }
            
            function SearchValueToTable(key, value, width){
                var width = (!!width) ? "width = '"+width+"' " : "";
                var content = "<table style='display:inline-block; width:100%; height:30px; vertical-align:middle'>";
                content += "<tr><td "+width+"><p style='margin:5px 10px 5px 5px;'>"+key+"</p></td><td>"+value+"</td></tr></table>";
                return content;
            }
            
            function newSelectForm(obj){
                var content = "<select id='"+obj.id+"' name='"+obj.name+"' size='1'/>";
                for (var i = 0; i < +obj.length; i++)
                 content += "<option value=" + ((!isNaN(obj[i].value)) ? obj[i].value : obj[i]) + ">" + ((!!obj[i].select) ? obj[i].select : obj[i]) + "</option>";
                content += "</select>";
                return content;
            }
            
            function addListenersToUsersSearchContent(search, city, sex, ageFrom, ageTo){
                search = search || "";
                city = city || "";
                sex = sex || "BOTH";
                ageFrom = ageFrom || "";
                ageTo = ageTo || "";
                var Search = document.getElementById('UsersSearchInput');
                var City = document.getElementById('CityInput');
                var Sex = document.getElementById('SexInput');
                var AgeFrom = document.getElementById('AgeFromInput');
                var AgeTo = document.getElementById('AgeToInput');
                var Button = document.getElementById('ContainerSearchButton');
                Search.value = cleanText(search);
                City.value = cleanText(city);
                Sex.value = sex;
                AgeFrom.value = ageFrom;
                AgeTo.value = ageTo;
                AgeFrom.onkeyup = function(){
                   var valueArray = this.value.split('');
                   for (var i = 0; i < valueArray.length; i++)
                        if ((isNaN(+valueArray[i])) || (valueArray[i] === ' '))  valueArray.splice(i,1); 
                   this.value = valueArray.join('');
                   if(+this.value > 150) this.value = 150;
                   
                   if (+this.value > AgeTo.value) AgeTo.value = this.value;
                };
                AgeTo.onkeyup = function(){
                   var valueArray = this.value.split('');
                    for (var i = 0; i < valueArray.length; i++)
                        if ((isNaN(+valueArray[i])) || (valueArray[i] === ' '))  valueArray.splice(i,1); 
                   this.value = valueArray.join('');
                   if(+this.value > 150) this.value = 150;
                   
                   if (+this.value < AgeFrom.value) AgeFrom.value = this.value;
                };
                Button.onclick = function(){
                    printUsers(Search.value, City.value, Sex.value, AgeFrom.value, AgeTo.value);
                };
            }
            
            function printUsers(search, city, sex, ageFrom, ageTo){
                search = search || "";
                city = city || "";
                sex = sex || "BOTH";
                ageFrom = ageFrom || 0;
                ageTo = ageTo || 0;
                clearTimeout(timerLastMessageId);
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getusers?search='+search+'&city='+city,true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var users = JSON.parse(xhr.responseText);
                    setContainerSearchContent();
                    addListenersToUsersSearchContent(search, city, sex, ageFrom, ageTo);
                    var CurrentTime = new Date();
                    var elem = document.getElementById('Container');
                    var content = "<li class='list-group-item Header' style='background:#dddddd;'>Users</li><li id='Users' class='list-group-item' style='margin-right:0px'>";
                    for (var i = 0; i < users.length; i++){
                        var user = users[i];
                        var Myself = (getCookie('username') === user.login) ? "" : "<tr   style='display:none'><td><span id='"+user.login+"' class='glyphicon glyphicon-envelope' onclick='getDialog(this)'></span></td></tr>";
                        content += "<div id='"+user.login+"' class='User PhotoFrame' onclick='getUser(this)'><span><img src='images/"+user.avatar+"low.jpg'/></span><p align='center' style='position:absolute; top:"+(170+209* Math.floor(i/4))+"px; left:"+(80+209*(i%4)-user.advNumber.split('').length)+"px; cursor: pointer; opacity:0.8'>"+user.login+"<br/><i style='font-size:0.8em; color:#eeeeee'>Age: "+user.age+", Advs: "+user.advNumber+"</i></p>";
                        content += "<table class='UserGlyphicons' style='position:absolute; top:"+(7+209* Math.floor(i/4))+"px; left:"+(21+209*(i%4))+"px; font-size:0.9em;'><tr><td><span class='glyphicon glyphicon-user'><i style='margin-left:4px;'>"+user.city+"</i></span></td></tr><tr style='display:none'><td><span class='glyphicon glyphicon-tasks'></span></td></tr>"+Myself+"</table>";
                        if ((CurrentTime - user.last_online_time) <= 10000) content += "<p style='position:absolute; top:"+(7+209* Math.floor(i/4))+"px; left:"+(185+209*(i%4))+"px; font-size:0.9em;'>Online</p>";
                        content += "</div>";
                    }
                    content += '</li>';
                    
                    elem.children[0].innerHTML = content;
                   $('.list-group-item > div').each(function(){
                        var elem = this;
                        this.onmouseout=handleMouseLeave(function(){
                                                    hideUsersGlyphicons(elem);
                                          });
                        this.onmouseover=handleMouseEnter(function(){
                                         showUsersGlyphicons(elem);
                                     });
                    });
                    var containerSearch = document.getElementById('ContainerSearch');
                    containerSearch.onmouseout=handleMouseLeave(function(){
                                                    ReduceElementOpacity = true;
                                                    reduceElementOpacity(this, false, 0.4);
                                          });
                    containerSearch.onmouseover=handleMouseEnter(function(){
                                                    EnlargeElementOpacity = true;
                                                    enlargeElementOpacity(this, 0.8);
                                     });         
                    containerSearch.style.display = 'block';          
                    NoMoreElements = (users.noMore === "true");
                    if (!NoMoreElements)
                       window.onscroll = function(){
                                        if (document.body.scrollTop > 50) {
                                            if (!containerSearch.style.position || containerSearch.style.position === 'absolute'){
                                                    containerSearch.style.position = 'fixed';
                                                    containerSearch.style.top = '10px';
                                            };
                                        }else{
                                            if (!containerSearch.style.position || containerSearch.style.position === 'fixed'){
                                                    containerSearch.style.position = 'absolute';
                                                    containerSearch.style.top = '60px';
                                            };
                                        }
                                       if ((document.body.scrollHeight - document.body.scrollTop - $(window).height() <= 500)&&(!NoMoreElements)) getMoreUsers(search, city, sex, ageFrom, ageTo);
                        };
                    elem.style.display = 'block';
                    
                };
                xhr.setRequestHeader('sex', sex);
                xhr.setRequestHeader('ageFrom', ageFrom);
                xhr.setRequestHeader('ageTo', ageTo);
                xhr.setRequestHeader('lastLogin', '');
                xhr.send('');
            };
            
            function getMoreUsers(search, city, sex, ageFrom, ageTo){
                 var xhr = new XMLHttpRequest();
                xhr.open('GET','getusers?search='+search+'&city='+city,true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var users = JSON.parse(xhr.responseText);
                    NoMoreElements = (users.noMore === "true");
                    var CurrentTime = new Date();
                    var elem = document.getElementById('Users');
                    var count = elem.children.length;
                    var content = "";
                    for (var i = 0; i < users.length; i++){
                        var user = users[i];
                        var Myself = (getCookie('username')===user.login) ? "" : "<tr   style='display:none'><td><span id='"+user.login+"' class='glyphicon glyphicon-envelope' onclick='getDialog(this)'></span></td></tr>";
                        content += "<div id='"+user.login+"' class='User PhotoFrame' onclick='getUser(this)'><span><img src='images/"+user.avatar+"low.jpg' /></span><p align='center' style='position:absolute; top:"+(170+209* Math.floor((i+count)/4))+"px; left:"+(80+209*(i%4)-user.advNumber.split('').length)+"px; cursor: pointer; opacity:0.8'>"+user.login+"<br/><i style='font-size:0.8em; color:#eeeeee'>Age: "+user.age+", Advs: "+user.advNumber+"</i></p>";
                        content += "<table class='UserGlyphicons' style='position:absolute; top:"+(7+209* Math.floor((i+count)/4))+"px; left:"+(21+209*((i+count)%4))+"px; font-size:0.9em;'><tr><td><span class='glyphicon glyphicon-user'><i style='margin-left:4px;'>"+user.city+"</i></span></td></tr><tr style='display:none'><td><span class='glyphicon glyphicon-tasks'></span></td></tr>"+Myself+"</table>";
                        if ((CurrentTime - user.last_online_time) <= 10000) content += "<p style='position:absolute; top:"+(7+209* Math.floor(i/4))+"px; left:"+(185+209*(i%4))+"px; font-size:0.9em;'>Online</p>";
                        content += "</div>";
                    }
                    if ($('.User').last().attr('id') < users[0].login){
                        elem.innerHTML += content;
                        
                        $('.list-group-item > div').each(function(){
                           var elem = this;
                           this.onmouseout=handleMouseLeave(function(){
                                                    hideUsersGlyphicons(elem);
                                          });
                           this.onmouseover=handleMouseEnter(function(){
                                         showUsersGlyphicons(elem);
                                     });
                        });
                    };
                };
                xhr.setRequestHeader('sex', sex);
                xhr.setRequestHeader('ageFrom', ageFrom);
                xhr.setRequestHeader('ageTo', ageTo);
                xhr.setRequestHeader('lastLogin', $('.User').last().attr('id'));
                xhr.send('');
            }
            
            
            function getUser(elem){
                window.onscroll = function(){};
                SetToZeroContainerSearch();
                clearTimeout(timerLastMessageId);
                var event = event || window.event;
                var user = elem.id || elem.alt || elem.innerHTML || elem;
                if (event)
                    if (event.stopPropagation){
                    event.stopPropagation();
                } else event.canselBuddle = true;
                cleanNavbar();
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getuser',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    document.body.scrollTop = 0;
                    var user = JSON.parse(xhr.responseText);
                    var container = document.getElementById('Container');
                    var content = "<li id = '"+user.login+"' class='list-group-item PersonalInformation' style='padding:4px 4px 4px 4px;'>";
                    var Myself = (getCookie('username')===user.login);
                    content += "<div id='"+user.login+"Frame' class='PhotoFrame' style='position:absolute; left:5px; height:166px; width:166px; border: 8px solid #dddddd; border-radius: 8px;'><span style='height:148px; width:148px;'><img id='userImg'  src='images/"+user.avatar+"low.jpg'  style='max-height:148px; max-width:148px;'/></span></div>";
                    content += "<p class='DialogName' style='position:absolute; left:180px; top:1px; font-size: 26px;'>"+user.login+"</p>";
                    content += "<table id='PersonalInformationTable' style='position:absolute; left:195px; top:50px; color:#777777;'><tbody><tr><td>"+((+user.sex===1) ? "male": "female")+"</td></tr><tr><td>"+getDate(+user.birthday)+"</td></tr><tr><td id='UserCity'><span class='glyphicon glyphicon-map-marker'></span> "+user.city+"</td></tr><tr><td><i style='font-size:0.8em;'>Age: "+user.age+", Advs: "+user.advNumber+"</i></td></tr></tbody></table>";
                    if (!Myself) content += "<span id='"+user.login+"' class='glyphicon glyphicon-envelope AnimateGlyph' style='position:absolute; top:10px; right:10px;' onclick='getDialog(this);'></span>";
                     else content += "<table style='position:absolute; top:10px; right:10px;'><tr><td><button id='addPhoto_"+user.login+"' onclick='addPhotoForm(this)'>Add Photo</button></td><td><button>Add Advertisement</button></td></tr></table>";
                    content += "<div  style='min-height:128px; width:673px; background:#f0f0f0; margin:40px 180px 5px 180px; border: 1px solid #dddddd; border-radius: 12px;'></div>";    
                    content += '</li>';
                    content += "<li id='Photos' class='list-group-item' style='margin-right:0px'>";
                    for (var i = 0; i < user.length; i++){
                        var photo = user[i];
                        var likedByUserClass = (photo.likedByUser==='true') ? "class='likedByUser PhotoFrame'" : "class='PhotoFrame'";
                        var opacity = (photo.likedByUser==='true') ? 'opacity:0.95;' : 'opacity:0;';
                        content += "<div id='photoId_"+photo.id+"' "+likedByUserClass+"   onclick='getPhoto(this);'><span><img src='images/"+photo.id+"low.jpg'/></span>";
                        if (Myself) content+="<div  style='position:absolute; top:"+(7+209* Math.floor(i/4))+"px; left:"+(198+209*(i%4))+"px; font-size:12px;'><span id='"+photo.id+"' class='glyphicon glyphicon-remove AnimateGlyph' onclick='removePhotoConfirmation(this);'></span></br><span id='"+photo.id+"' class='glyphicon glyphicon-picture AnimateGlyph' onclick='changeAvatar(this);'></span></div>";
                        content += "<p align='center' style='position:absolute; top:"+(190+209* Math.floor(i/4))+"px; left:"+(85+209*(i%4))+"px; '><span id='"+photo.id+"' class='glyphicon glyphicon-heart' style='position:relative; "+opacity+" top:1px; margin-right:5px'></span><i  style='"+opacity+"'>"+photo.likeNumber+"</i><span id='"+photo.id+"' class='glyphicon glyphicon-comment' style='position:relative; "+opacity+" top:1px; margin-left:10px; margin-right:5px'></span><i style='"+opacity+"'>"+photo.commentNumber+"</i></p>";
                        content += "</div>";
                    }
                    content += '</li>';
                    container.children[0].innerHTML = content;
                    $('div[id^="photoId_"]').each(function(){
                        var elem = this;
                        var glyphicons = this.lastChild.children;
                        
                        function Clicked(){
                            var event = event || window.event;
                             if (event.stopPropagation){
                             event.stopPropagation();
                             } else event.canselBuddle = true;
                            if ( elem.className.indexOf('likedByUser')!==-1){
                                elem.className=elem.className.substring(0,elem.className.indexOf('likedByUser')) + elem.className.substring(elem.className.indexOf('likedByUser')+11,elem.className.split("").length);
                                this.parentNode.children[0].style.opacity = 0.7;
                                this.parentNode.children[1].style.opacity = 0.7;
                                this.parentNode.children[2].style.opacity = 0.7;
                                this.parentNode.children[3].style.opacity = 0.7;
                                this.parentNode.children[1].innerHTML -=1;
                                elem.onmouseout=handleMouseLeave(function(){
                                         ReduceElementOpacity = true;
                                         var glyphicons = this.lastChild.children;
                                              reduceElementOpacity(glyphicons); 
                                          });
                                elem.onmouseover=handleMouseEnter(function(){
                                         EnlargeElementOpacity = true;
                                          var glyphicons = this.lastChild.children;
                                               enlargeElementOpacity(glyphicons,0.7, 'inline');
                                           });         
                             DeletePhotoLike(elem.id.substring(8));
                            }else{
                                elem.className+=" likedByUser";
                                this.parentNode.children[0].style.opacity = 0.95;
                                this.parentNode.children[1].style.opacity = 0.95;
                                this.parentNode.children[2].style.opacity = 0.95;
                                this.parentNode.children[3].style.opacity = 0.95;
                                this.parentNode.children[1].innerHTML = +this.parentNode.children[1].innerHTML + 1;
                                elem.onmouseout=function(){};
                                elem.onmouseover=function(){};
                                AddPhotoLike(elem.id.substring(8));
                             }
                        } 
                        glyphicons[0].onclick = Clicked;
                        glyphicons[1].onclick = Clicked;
                        
                        if ( this.className.indexOf('likedByUser')===-1){
                           this.onmouseout=handleMouseLeave(function(){
                                         ReduceElementOpacity = true;
                                         var glyphicons = this.lastChild.children;
                                              reduceElementOpacity(glyphicons); 
                                          });
                           this.onmouseover=handleMouseEnter(function(){
                                         EnlargeElementOpacity = true;
                                          var glyphicons = this.lastChild.children;
                                               enlargeElementOpacity(glyphicons,0.7, 'inline');
                                     });
                        }
                    });
                    container.style.display = 'block';
                    NoMoreElements = (user.noMore === "true");
                    if (!NoMoreElements)
                       window.onscroll = function(){
                                       if ((document.body.scrollHeight - document.body.scrollTop - $(window).height()  <= 500)&&(!NoMoreElements)) getMorePhotos();
                        };
                    
                };
                xhr.setRequestHeader('user', user);
                xhr.setRequestHeader('lastId', '-1');
                xhr.send('');
            };
            
            function addPhotoForm(elem){
                var containerList = document.getElementById('ContainerList');
                var content = "<li class='list-group-item'><form enctype='multipart/form-data' action='loadphoto' method='post' name='loadPhoto' target='hiddenframe'><input type='hidden' name='MAX_FILE_SIZE' value='64000'><input id='fileInput' type='file' name='photo' accept='image/jpeg'><p>Description</p><textarea id='TextArea' class='noscroll' name='text'></textarea><textarea id='TextAreaNoDisplay' class='noscroll' name='description' style='display:none;'></textarea></form> <iframe id='hiddenframe' name='hiddenframe' style='width:0px; height:0px; border:0px'></iframe><button id='SendPhotoButton'>Send</button></li>";
                containerList.innerHTML = content;
                ChangeTextAreaSize();
                document.getElementById('SendPhotoButton').onclick = function(){
                    if (document.getElementById('fileInput').files.length === 1){
                     document.getElementById('TextAreaNoDisplay').value = cleanText(document.getElementById('TextArea').value);
                     document.forms["loadPhoto"].submit();
                     setTimeout(function(){containerList.innerHTML = "<li class='list-group-item' style='height:400px; text-align:center;'><p>Photo uploaded</p></li>";},500);
                    }
                };
            };
            
            function removePhotoConfirmation(elem){
                var event = event || window.event;
                if (event.stopPropagation){
                    event.stopPropagation();
                } else event.canselBuddle = true;
                result = confirm("Are you sure?");
                if (result) removePhoto(elem);
            }
            
            function removePhoto(elem){
                var photo = elem.id ;
                var xhr = new XMLHttpRequest();
                xhr.open('DELETE','deletephoto',true);
                xhr.onreadystatechange = function() {
                    if (this.readyState !== 4) return;
                    if (0 === +getCookie('useravatar')){
                        if (!!document.getElementById('userImg')) document.getElementById('userImg').src = "images/0low.jpg";
                        document.getElementById('HomeLiUserImg').src = "images/0low.jpg";
                    }
                    if (document.getElementById('ContainerList').firstChild.id)
                        {
                        var photoFrame = document.getElementById('photoId_'+photo);
                    photoFrame.parentNode.removeChild(photoFrame);
                    if (document.getElementById('Photos')){
                     var photos = document.getElementById('Photos').children;
                     for (var i = 0; i < photos.length; i++){
                        photos[i].children[1].style.top = (7+209* Math.floor(i/4)) + "px";
                        photos[i].children[1].style.left = (198+209*(i%4)) + "px";
                        photos[i].children[2].style.top = (190+209* Math.floor(i/4)) + "px";
                        photos[i].children[2].style.left = (85+209*(i%4)) + "px";
                     };
                    };    
                    }else{
                       document.getElementById('ContainerList').innerHTML = "";
                   };
                };
                xhr.setRequestHeader("photoId",photo);
                xhr.send('');
            }
            
            function changeAvatar(elem){
                var event = event || window.event;
                if (event.stopPropagation){
                    event.stopPropagation();
                } else event.canselBuddle = true;
                var photo = elem.id ;
                var xhr = new XMLHttpRequest();
                xhr.open('POST','setavatar',true);
                xhr.onreadystatechange = function() {
                    if (this.readyState !== 4) return;
                    document.getElementById('HomeLiUserImg').src = "images/"+getCookie("useravatar")+"low.jpg";
                    document.getElementById('userImg').src = "images/"+getCookie("useravatar")+"low.jpg";
                };
                xhr.setRequestHeader("photoId",photo);
                xhr.send('');
            }
            
            function getMorePhotos(){
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getuser',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var photos = JSON.parse(xhr.responseText);
                    NoMoreElements = (photos.noMore === "true");
                    var PhotoContainer = document.getElementById('Photos');
                    var Myself = (getCookie('username')===photos.login);
                    if (PhotoContainer) {
                         var count = PhotoContainer.children.length;
                         var content = "";
                         for (var i = 0; i < photos.length; i++){
                             var photo = photos[i];
                             var likedByUserClass = (photo.likedByUser==='true') ? "class='likedByUser PhotoFrame'" : "class='PhotoFrame'";
                             var opacity = (photo.likedByUser==='true') ? 'opacity:0.95;' : 'opacity:0;';
                            content += "<div id='photoId_"+photo.id+"' "+likedByUserClass+" onclick='getPhoto(this);'><span><img src='images/"+photo.id+"low.jpg'/></span>";
                            if (Myself) content+="<div  style='position:absolute; top:"+(7+209* Math.floor((i + count)/4))+"px; left:"+(198+209*((i + count)%4))+"px; font-size:12px;'><span id='"+photo.id+"' class='glyphicon glyphicon-remove  AnimateGlyph' onclick='removePhotoConfirmation(this);'></span></br><span id='"+photo.id+"' class='glyphicon glyphicon-picture AnimateGlyph' onclick='changeAvatar(this);'></span></div>";
                            content += "<p align='center' style='position:absolute; top:"+(190+209* Math.floor((i + count)/4))+"px; left:"+(85+209*((i + count)%4))+"px; '><span id='"+photo.id+"' class='glyphicon glyphicon-heart' style='position:relative; "+opacity+" top:1px; margin-right:5px'></span><i  style='"+opacity+"'>"+photo.likeNumber+"</i><span id='"+photo.id+"' class='glyphicon glyphicon-comment' style='position:relative; "+opacity+" top:1px; margin-left:10px; margin-right:5px'></span><i style='"+opacity+"'>"+photo.commentNumber+"</i></p>";
                            content += "</div>";
                        }
                         var lastPhoto = $('div[id^="photoId_"]').last();
                         if (+lastPhoto.attr('id').substring(8) > photos[0].id)
                         {
                          PhotoContainer.innerHTML += content;
                         
                          $('div[id^="photoId_"]').each(function(){
                             var elem = this;
                             var glyphicons = this.lastChild.children;
                        
                          function Clicked(){
                              var event = event || window.event;
                               if (event.stopPropagation){
                               event.stopPropagation();
                                  } else event.canselBuddle = true;
                                 if ( elem.className.indexOf('likedByUser')!==-1){
                                    elem.className=elem.className.substring(0,elem.className.indexOf('likedByUser')) + elem.className.substring(elem.className.indexOf('likedByUser')+11,elem.className.split("").length);
                                    this.parentNode.children[0].style.opacity = 0.7;
                                    this.parentNode.children[1].style.opacity = 0.7;
                                    this.parentNode.children[2].style.opacity = 0.7;
                                    this.parentNode.children[3].style.opacity = 0.7;
                                    this.parentNode.children[1].innerHTML -=1;
                                    elem.onmouseout=handleMouseLeave(function(){
                                         ReduceElementOpacity = true;
                                         var glyphicons = this.lastChild.children;
                                              reduceElementOpacity(glyphicons); 
                                          });
                                    elem.onmouseover=handleMouseEnter(function(){
                                         EnlargeElementOpacity = true;
                                          var glyphicons = this.lastChild.children;
                                               enlargeElementOpacity(glyphicons,0.7, 'inline');
                                           });         
                                    DeletePhotoLike(elem.id.substring(8));
                                 }else{
                                      elem.className+=" likedByUser";
                                      this.parentNode.children[0].style.opacity = 0.95;
                                      this.parentNode.children[1].style.opacity = 0.95;
                                      this.parentNode.children[2].style.opacity = 0.95;
                                      this.parentNode.children[3].style.opacity = 0.95;
                                      this.parentNode.children[1].innerHTML = +this.parentNode.children[1].innerHTML + 1;
                                      elem.onmouseout=function(){};
                                      elem.onmouseover=function(){};
                                      AddPhotoLike(elem.id.substring(8));
                                }
                            } 
                            glyphicons[0].onclick = Clicked;
                            glyphicons[1].onclick = Clicked;
                        
                            if ( this.className.indexOf('likedByUser')===-1){
                            this.onmouseout=handleMouseLeave(function(){
                                         ReduceElementOpacity = true;
                                         var glyphicons = this.lastChild.children;
                                              reduceElementOpacity(glyphicons); 
                                          });
                            this.onmouseover=handleMouseEnter(function(){
                                         EnlargeElementOpacity = true;
                                          var glyphicons = this.lastChild.children;
                                               enlargeElementOpacity(glyphicons,0.7, 'inline');
                                     });
                            }
                          });
                         };
                     };
                };
                xhr.setRequestHeader('user', $('.PersonalInformation').first().attr('id'));
                xhr.setRequestHeader('DescId', $('div[id^="photoId_"]').last().attr('id').substring(8));
                xhr.send('');
            }
            
            function getPhoto(elem){
                window.onscroll = function(){};
                var photoId = elem.id.substring(8);
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getphoto',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var comments = JSON.parse(xhr.responseText);
                    var content = "";
                    
                    NoMoreElements = (comments.noMore === "true");
                    if (!NoMoreElements) content = "<li id='photoId_"+comments.photoId+"' class='list-group-item MorePhotoComments' style='background:#eeeeee; height:30px; padding:0px 0px 0px 10px; cursor:pointer;' onclick='getMorePhotoComments(this);'><span class='glyphicon glyphicon-plus  AnimateGlyph' style = 'position:absolute; left: 50%;'></span></li>"; 
                    if (comments.length > 0){
                      for (var i =  comments.length-1; i > -1; i--){
                        var comment = comments[i];
                        content+="<li id ='commentLi_"+comment.id+"'  class='list-group-item' style='background:#fdfdfd;'>";
                        content+="<table id='commentId_"+comment.id+"' style='display:inline-block; vertical-align: top; max-width:75%;><tr><td style='padding-right:10px'><div class='PhotoFrame' style='width:80px; height:80px; border:0px; margin:0px 0px 0px 0px;'><span style='width:78px; height:78px;'><img src='images/"+comment.avatarFrom+"low.jpg' alt='"+comment.userFrom+"' style='max-width:78px; max-height:78px;' onclick='getUser(this)' style='cursor: pointer;'/></span></div></td><td><p onclick='getUser(this)' class='DialogName'>"+comment.userFrom+"</p>";
                        content+="<div>"  + CommentToTable(comment.value) + "</div></td></tr></table><div style = 'position:absolute; bottom:40%; right:30px; font-size: 0.8em; color: #999999;' >"+getDate(comment.date)+"</div>";
                        if (comment.userFrom === getCookie("username") || comments.user === getCookie("username")) content +="<span id='remove"+comment.id+"' class='glyphicon glyphicon-remove  AnimateGlyph' style='float:right' onclick='removeComment(this)'></span>";
                        content +="</li>";
                      }
                    }else{
                        content = "<li id='NoOneComment' class='list-group-item' style='height:30px; text-align:center; background:#fdfdfd; color:#777777;'><i>No one comment...</i></li>"; 
                    }
                    var likedByUser = (comments.likedByUser === "true") ? "likedByUser" : "";
                    var likedByUserColor =(likedByUser) ? "color:white;" : "";
                    content = "<li class='list-group-item "+likedByUser+"' style=' background:#dddddd; height:32px; color:#f0f0f0; text-shadow: #aaaaaa 1px 1px 0; padding:0px 10px 0px 10px; text-align:center;'><span id = 'likeHeart"+comments.photoId+"' class='glyphicon glyphicon-heart' style='position:relative; "+likedByUserColor+" top:1px; margin-right:5px'><div id='UsersWhoLikeIt' style='display:none; position:absolute; z-index:1000;'></div></span><i style='cursor:pointer; "+likedByUserColor+"' >"+comments.likeNumber+"</i><span  class='glyphicon glyphicon-comment' style='position:relative; top:1px; margin-left:10px; margin-right:5px'></span><i style='cursor:pointer;'>"+comments.commentNumber+"</i></li>" + content;
                    content = "<li class='list-group-item' style=' background:#dddddd; height:32px; padding:0px 0px 0px 10px'><div class='PhotoFrame' style='display:inline-block; width:30px; height:30px; border:0px; margin:0px 0px 10px 0px;'><span style='width:28px; height:28px;'><img src='images/"+comments.userAvatar+"low.jpg' alt='"+comments.user+"' style='max-width:28px; max-height:28px;' onclick='getUser(this)' style='cursor: pointer;'/></span></div><div id='"+comments.user+"' class='DialogName' onclick='getUser(this)' style='display:inline-block; position:relative; top: -3px;  height:30px;'> "+comments.user+"</div><span id='"+comments.photoId+"' class='glyphicon glyphicon-remove  AnimateGlyph' style='position:absolute; left:845px; z-index:900;' onclick='removePhotoConfirmation(this);'></span></li><li class='list-group-item' style='background:#ffffff; text-align:center; background:black;'><img src='images/"+comments.photoId+".jpg' style='display:inline; max-height:100%; max-width:100%;'><div style='position:absolute; bottom:10px; width:97%;color:#fdfdfd; text-align:center; opacity:0.8;'>"+comments.description+"</div></li>" + content;
                    content+="<li id='AddCommentPart' class='list-group-item' style='background:#cccccc;'>";
                    content+="<textarea id='TextArea' class='noscroll' name='text'>";
                    content+="</textarea><button name='"+comments.photoId+"' onclick='AddPhotoComment(this)' style='margin-left:94%; font-size: 0.8em; color: #999999;'>Send</button></li>";
                    
                    document.getElementById('ContainerList').innerHTML = content;
                    var likeHeart = document.getElementById('likeHeart'+comments.photoId+'');
                    likeHeart.onclick = function(){
                         var parent = this.parentNode;
                        if ( parent.className.indexOf('likedByUser')!==-1){
                                parent.className=parent.className.substring(0,parent.className.indexOf('likedByUser')) + parent.className.substring(parent.className.indexOf('likedByUser')+11,parent.className.split("").length);
                                this.style.color = "#f0f0f0";
                                this.nextSibling.style.color = "#f0f0f0";
                                this.nextSibling.innerHTML = +this.nextSibling.innerHTML - 1;
                                DeletePhotoLike(this.id.substring(9));
                        }else{
                                parent.className += ' likedByUser';
                                this.style.color = "white";
                                this.nextSibling.style.color = "white";
                                this.nextSibling.innerHTML = +this.nextSibling.innerHTML + 1;
                                AddPhotoLike(this.id.substring(9));
                        };
                    };
                    var likes = document.getElementById('UsersWhoLikeIt');
                    likeHeart.onmouseover = handleMouseEnter(function(){
                                               EnlargeElementOpacity = true;
                                               if (likes.innerHTML==="") getPhotoLikes(this);
                                               enlargeElementOpacity(likes,0.8, 'block');
                                     });
                    likeHeart.onmouseout=handleMouseLeave(function(){
                                              ReduceElementOpacity = true;
                                              likes.innerHTML="";
                                              reduceElementOpacity(likes); 
                                          });                 
                    document.getElementById('Container').style.display = 'block';
                    
                        
                    ChangeTextAreaSize();
                };
                xhr.setRequestHeader("photoId",photoId);
                xhr.send('');
            };
            function getPhotoLikes(elem){
                var event = event || window.event;
                if (event.stopPropagation){
                    event.stopPropagation();
                } else event.canselBuddle = true;
                var div = document.getElementById('UsersWhoLikeIt');
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getphotolikes',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    console.log(xhr.responseText);
                    var likes = JSON.parse(xhr.responseText);
                    var content = "<table><tr>";
                    var NoMoreBefore = (likes.noMoreBefore === 'true');
                    var NoMoreAfter = (likes.noMoreAfter === 'true');
                    if(!NoMoreBefore) content += "<td id='photoId__"+likes.photoId+"' class='arrowLeft' style='cursor:pointer;' onclick='getPhotoLikes(this)'><span class='glyphicon glyphicon-chevron-left' style='color:#555555;'></span></td>";
                    content += "<td style='min-width:150px;'>";
                    for (var i = 0; i < likes.length; i++){
                        var like = likes[i];
                        content += "<div class='PhotoFrame' style='float:left; width:30px; height:30px; border:0px; margin:0px 0px 0px 0px;'><span  id = 'photoFrame_"+i+"' style='width:28px; height:28px;'><img src='images/"+like.avatarFrom+"low.jpg' alt='"+like.userFrom+"' title='"+like.userFrom+"' style='max-width:28px; max-height:28px;' onclick='getUser(this)' style='cursor: pointer;'/></span></div>";
                        if (i === 4) content += "<br/>";
                    }
                    content += "</td>";
                    if(!NoMoreAfter) content += "<td id='photoId__"+likes.photoId+"' class='arrowRight' style='cursor:pointer;'  onclick='getPhotoLikes(this)'><span class='glyphicon glyphicon-chevron-right' style='color:#555555;'></span></td>";
                    content += "</tr></table>";
                    div.innerHTML = content;
                    };
                var next = (elem.className.indexOf("arrowLeft") === -1);
                var lastUser;
                if (div.innerHTML === ""){
                    lastUser = "";
                }else {
                    lastUser = (next) ? $('#photoFrame_9').children(0).attr('alt') : $('#photoFrame_0').children(0).attr('alt');
                }
                xhr.setRequestHeader("photoId",elem.id.substring(9));
                xhr.setRequestHeader("lastUser",lastUser);
                xhr.setRequestHeader('next',next);
                xhr.send('');
            };
            
            function getMorePhotoComments(elem){
                var photoId = elem.id.substring(8);
                var lastComment = $('table[id^="commentId_"]').first();
                var lastCommentId = lastComment.attr("id").substring(10);
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getmorephotocomments',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                     var comments = JSON.parse(xhr.responseText);
                     NoMoreElements = (comments.noMore === "true");
                     if (NoMoreElements) elem.remove();
                     if (comments.length > 0){
                      for (var i =  0; i < comments.length; i++){
                        var comment = comments[i];
                        var newCommentLi = document.createElement('li');
                        newCommentLi.id = "commentLi_"+comment.id;
                        newCommentLi.className = "list-group-item";
                        newCommentLi.style.background = "#fdfdfd";
                        var content ="<table id='commentId_"+comment.id+"' style='display:inline-block; vertical-align: top;'><tr><td style='padding-right:10px'><div class='PhotoFrame' style='width:80px; height:80px; border:0px; margin:0px 0px 0px 0px;'><span style='width:78px; height:78px;'><img src='images/"+comment.avatarFrom+"low.jpg' alt='"+comment.userFrom+"' style='max-width:78px; max-height:78px;' onclick='getUser(this)' style='cursor: pointer;'/></span></div></td><td><p onclick='getUser(this)' class='DialogName'>"+comment.userFrom+"</p>";
                        content+="<div style='cursor: pointer;'>" + CommentToTable(comment.value) + "</div></td></tr></table><div class='DialogTime'>"+getDate(comment.date)+"</div>";
                        if (comment.user === getCookie("username") || comments.user === getCookie("username")) content +="<span id='remove"+comment.id+"' class='glyphicon glyphicon-remove  AnimateGlyph' style='float:right' onclick='removeComment(this)'></span>";
                        content +="</li>";
                        newCommentLi.innerHTML = content;
                        document.getElementById("ContainerList").insertBefore(newCommentLi, $('li[id^="commentLi_"]').toArray()[0]);
                      }
                    }
                };
                xhr.setRequestHeader("photoId",photoId);
                xhr.setRequestHeader("lastCommentId",lastCommentId);
                xhr.send('');
                
            }
            
            function removeComment(elem){
                var commentId = elem.id.substring(6);
                var xhr = new XMLHttpRequest();
                xhr.open('DELETE','deletephotocomment',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var parent = elem.parentNode;
                    var GrandParent = parent.parentNode;
                    GrandParent.removeChild(parent);
                    var ContainerList = document.getElementById("ContainerList");
                    if (ContainerList.children.length === 3){
                        var noOneCommentLi = document.createElement('li'); 
                        noOneCommentLi.id = "NoOneComment";
                        noOneCommentLi.className = "list-group-item";
                        noOneCommentLi.style.cssText = 'height:30px; text-align:center; background:#fdfdfd; color:#777777;';
                        var content = "<i>No one comment...</i>"; 
                        noOneCommentLi.innerHTML = content;
                        ContainerList.insertBefore(noOneCommentLi, document.getElementById("AddCommentPart"));
                    }
                };
                xhr.setRequestHeader("commentId",commentId);
                xhr.send('');
                
            }
            
            function AddPhotoComment(elem){
                var text = elem.previousSibling.value;
                text = cleanText(text.replace(/[\r\n]/g,':.!'));
                elem.previousSibling.value = '';
                var xhr = new XMLHttpRequest();
                xhr.open('POST','addphotocomment?value='+text,true);
                xhr.onreadystatechange = function() {
                    if (this.readyState !== 4) return;
                    var comment = JSON.parse(xhr.responseText);
                    var newCommentLi = document.createElement('div');
                    newCommentLi.id = comment.id;
                    newCommentLi.className = "list-group-item";
                    newCommentLi.style.background = "#fdfdfd";
                    var content ="<table id='commentId_"+comment.id+"' style='display:inline-block; vertical-align: top;'><tr><td style='padding-right:10px'><div class='PhotoFrame' style='width:80px; height:80px; border:0px; margin:0px 0px 0px 0px;'><span style='width:78px; height:78px;'><img src='images/"+comment.avatarFrom+"low.jpg' alt='"+comment.userFrom+"' style='max-width:78px; max-height:78px;' onclick='getUser(this)' style='cursor: pointer;'/></span></div></td><td><p onclick='getUser(this)' class='DialogName'>"+comment.userFrom+"</p>";
                    content+="<div style='cursor: pointer;'>" + CommentToTable(comment.value) + "</div></td></tr></table><div class='DialogTime'>"+getDate(comment.date)+"</div>";
                    content +="<span id='remove"+comment.id+"' class='glyphicon glyphicon-remove  AnimateGlyph' style='float:right' onclick='removeComment(this)'></span>";
                    newCommentLi.innerHTML = content;
                    var noOneComment = document.getElementById("NoOneComment");
                    if (noOneComment) document.getElementById("ContainerList").removeChild(noOneComment);
                    document.getElementById("ContainerList").insertBefore(newCommentLi, document.getElementById("AddCommentPart"));
                    
                };
                xhr.setRequestHeader("photoId",elem.name);
                xhr.send('');
            };
            
            function AddPhotoLike(photoId){
                var xhr = new XMLHttpRequest();
                xhr.open('POST','addphotolike',true);
                xhr.setRequestHeader("photoId",photoId);
                xhr.send('');
            }
            
            function DeletePhotoLike(photoId){
                var xhr = new XMLHttpRequest();
                xhr.open('DELETE','deletephotolike',true);
                xhr.setRequestHeader("photoId",photoId);
                xhr.send('');
            }
            
            
            function getUserFromSearch(elem){
                SearchMouseOut = true;
                ReduceElementOpacity = true;
                $('#Search-li-dropdown > ul').get(0).innerHTML= "";
                $('#Search-li > input').val("");
                getUser(elem);
            }
            
            function showUsersGlyphicons(elem){
                 EnlargeElementOpacity = true;
              if ($("#"+elem.id+" tr").get(2) !== undefined)
              enlargeElementOpacity([$("#"+elem.id+" tr").get(1),$("#"+elem.id+" tr").get(2)],1);
              else
                 enlargeElementOpacity($("#"+elem.id+" tr").get(1),1);
            
            }
            
            function hideUsersGlyphicons(elem){
                ReduceElementOpacity = true;
               if ($("#"+elem.id+" tr").get(2) !== undefined)
                 reduceElementOpacity([$("#"+elem.id+" tr").get(1),$("#"+elem.id+" tr").get(2)]);
              else
                 reduceElementOpacity($("#"+elem.id+" tr").get(1));
            }
            
            function printDialogs(){
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getdialogs',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    console.log(xhr.responseText);
                    var dialogs = JSON.parse(xhr.responseText);
                    var elem = document.getElementById('Container');
                    var content = "<li class='list-group-item Header' style='background:#dddddd;'>Dialogs</li>";
                    for (var i = 0; i < dialogs.length; i++){
                        var dialog = dialogs[i];
                        var other = (dialog.to_id === dialog.user)? dialog.from_id : dialog.to_id;
                        var unreadToUser = (dialog.unread==='true') && (dialog.to_id === dialog.user) ? "style='background: #e9e9e9; padding: 0px 0px;'" : "style='padding: 0px 0px;'";
                        var unread = (dialog.unread==='true') ? "class='unreadMessage'" :"class='notUnreadMessage'";
                        content+="<li id ='"+other+"'  class='list-group-item' onclick='getDialog(this)' "+unreadToUser+">";
                        content+="<table id='messageId_"+dialog.id+"' style='display:inline-block; vertical-align: top;'><tr><td style='padding-right:10px'><div class='PhotoFrame' style='width:80px; height:80px; border:0px; margin:0px 0px 0px 0px;'><span style='width:78px; height:78px;'><img src='images/"+dialog.otherAvatar+"low.jpg' alt='"+other+"' style='max-width:78px; max-height:78px;' onclick='getUser(this)' style='cursor: pointer;'/></span></div></td><td><p onclick='getUser(this)' class='DialogName'>"+other+"</p>";
                        var value = (dialog.value.split('').length > 80) ? dialog.value.substring(0,80)+"..." : dialog.value;
                        var enter = value.indexOf(':.!');
                        value = enter !== -1 ? value.substring(0,enter)+"..." : value;
                        if (dialog.from_id === dialog.user) content+="<div " + unread + " style='cursor: pointer;'><div class='PhotoFrame' style='float:left; position:relative; bottom:4px;right:4px; margin:0px 5px 1px 1px; width:27px; height:27px; border:0px;'><span style='width:25px; height:25px;'><img src='images/"+getCookie('useravatar')+"low.jpg' alt='"+getCookie('useravatar')+"' style='max-width:25px; max-height:25px;' style='cursor: pointer;'/></span></div>" + " " + value + "</div></td></tr></table><div class='DialogTime' >"+getDate(dialog.date)+"</div><span id='"+other+"' class='glyphicon glyphicon-remove  AnimateGlyph' style='float:right;' onclick='RemoveDialogConfirmation(this)'></span></li>";
                        else content+="<div " + unread + " style='cursor: pointer;'>" + value + "</div></td></tr></table><div class='DialogTime'>"+getDate(dialog.date)+"</div><span id='"+other+"' class='glyphicon glyphicon-remove  AnimateGlyph' style='float:right' onclick='RemoveDialogConfirmation(this)'></span></li>";
                    }
                    elem.children[0].innerHTML = content;
                    elem.style.display = 'block';
                    NoMoreElements = (dialogs.noMore === "true");
                    if (!NoMoreElements)
                       window.onscroll = function(){
                                       if ((document.body.scrollHeight - document.body.scrollTop - $(window).height() <= 500)&&(!NoMoreElements)) getMoreDialogs();
                        };
                    timerLastMessageId = setInterval(checkLastDialogsMessageId,2000);
                };
                xhr.setRequestHeader("lastId", -1);
                xhr.send('');
            };
            
            function getMoreDialogs(){
                 var xhr = new XMLHttpRequest();
                xhr.open('GET','getdialogs',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var dialogs = JSON.parse(xhr.responseText);
                    NoMoreElements = (dialogs.noMore === "true");
                    var content = "";
                    for (var i = 0; i < dialogs.length; i++){
                        var dialog = dialogs[i];
                        var other = (dialog.to_id === dialog.user)? dialog.from_id : dialog.to_id;
                        var unreadToUser = (dialog.unread==='true') && (dialog.to_id === dialog.user) ? "style='background: #e9e9e9; padding: 0px 0px;'" : "style='padding: 0px 0px;'";
                        var unread = (dialog.unread==='true') ? "class='unreadMessage'" :"class='notUnreadMessage'";
                        content+="<li id ='"+other+"'  class='list-group-item' onclick='getDialog(this)' "+unreadToUser+">";
                        content+="<table id='messageId_"+dialog.id+"' style='display:inline-block; vertical-align: top;'><tr><td style='padding-right:10px'><div class='PhotoFrame' style='width:80px; height:80px; border:0px; margin:0px 0px 0px 0px;'><span style='width:78px; height:78px;'><img src='images/"+dialog.otherAvatar+"low.jpg' alt='"+other+"' style='max-width:78px; max-height:78px;' onclick='getUser(this)' style='cursor: pointer;'/></span></div></td><td><p onclick='getUser(this)' class='DialogName'>"+other+"</p>";
                        var value = (dialog.value.split('').length > 80) ? dialog.value.substring(0,80)+"..." : dialog.value;
                        var enter = value.indexOf(':.!');
                        value = enter !== -1 ? value.substring(0,enter)+"..." : value;
                        if (dialog.from_id === dialog.user) content+="<div " + unread + " style='cursor: pointer;'><img src='1.jpg' width='27' heigth='27' style='float:left; position:relative; bottom:4px;right:4px; margin-right:5px'/>" + " " + value + "</div></td></tr></table><div class='DialogTime' >"+getDate(dialog.date)+"</div><span id='"+other+"' class='glyphicon glyphicon-remove  AnimateGlyph' style='float:right;' onclick='RemoveDialogConfirmation(this)'></span></li>";
                        else content+="<div " + unread + " style='cursor: pointer;'>" + value + "</div></td></tr></table><div class='DialogTime'>"+getDate(dialog.date)+"</div><span id='"+other+"' class='glyphicon glyphicon-remove  AnimateGlyph' style='float:right' onclick='RemoveDialogConfirmation(this)'></span></li>";
                    
                    }
                    if (+$('table[id^="messageId_"]').last().attr('id').substring(10) > +dialogs[0].id){
                        document.getElementById('Container').children[0].innerHTML += content;
                    };
                    
                    
                };
                xhr.setRequestHeader("lastId", $('table[id^="messageId_"]').last().attr('id').substring(10));
                xhr.send('');
            }
            
            function getMonth(month){
                    switch(month){
                        case 0: return('Jan');  
                            break;
                        case 1: return('Feb');  
                            break;
                        case 2: return('Mar');  
                            break;
                        case 3: return('Apr');  
                            break;
                        case 4: return('May');  
                            break;
                        case 5: return('Jun');  
                            break;   
                        case 6: return('Jul');  
                            break;
                        case 7: return('Aug');  
                            break;
                        case 8: return('Sep');  
                            break;
                        case 9: return('Oct');  
                            break;
                        case 10: return('Nov');  
                            break;
                        case 11: return('Dec');  
                            break;
                     }
            }
            
            function getDate(millis){
                
                today = new Date();
                current = new Date(+millis);
                if (today.getFullYear()===current.getFullYear())
                     {
                       if ((today.getDate()===current.getDate())&& (today.getMonth()===current.getMonth())) return (current.getHours()+":"+((current.getMinutes() < 10) ? ('0'+current.getMinutes()) : current.getMinutes()));
                       if ((today.getDate()===current.getDate()+1)&& (today.getMonth()===current.getMonth())) return "yesterday";
                       return ((getMonth(current.getMonth())+" "+current.getDate()));
                     }
                     
                return (current.getDate()+" "+getMonth(current.getMonth())+" "+current.getFullYear());   
            };
            
            function RemoveDialogConfirmation(elem){
                var event = event || window.event;
                if (event.stopPropagation){
                    event.stopPropagation();
                } else event.canselBuddle = true;
                result = confirm("Are you sure?");
                if (result) RemoveDialog(elem);
            };
            
            function RemoveDialog(elem){
                var xhr = new XMLHttpRequest();
                xhr.open('GET','deletedialog',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var parent = elem.parentNode;
                    var GrandParent = parent.parentNode;
                    if (parent.id === ''){
                        GrandParent.innerHTML = "";
                    }else{
                    GrandParent.removeChild(parent);
                    }
                    NumberOfUnreadDialogs();
                };
                xhr.setRequestHeader("other",elem.id);
                xhr.send('');
            };
            
            function SetToZeroContainerSearch(){
                var container = document.getElementById('ContainerSearch');
                container.style.display = 'none';
                container.style.position = 'absolute';
                container.style.top = '60px'; 
            }
            
            function getDialog(elem){
                window.onscroll = function(){};
                SetToZeroContainerSearch();
                clearTimeout(timerLastMessageId);
                var event = event || window.event;
                if (event.stopPropagation){
                    event.stopPropagation();
                } else event.canselBuddle = true;
                
                cleanNavbar();
                
                var other = elem.id || elem;
               if (getCookie('username')!==other) {
                var xhr = new XMLHttpRequest();
                xhr.open('GET','getoldmessages',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var messages = JSON.parse(xhr.responseText);
                    var other = messages.other;
                    
                    var content = "";
                    if (messages.length > 0){
                      for (var i = 0; i < messages.length; i++){
                        var message = messages[i];
                       content = "<tr><td>" + MessageToTable(message)+"</td></tr>"+content;
                      }
                    }
                    content = "<li class='list-group-item' style=' background:#dddddd; height:32px; padding:0px 0px 0px 10px'><div class='PhotoFrame' style='display:inline-block; width:30px; height:30px; border:0px; margin:0px 0px 10px 0px;'><span style='width:28px; height:28px;'><img src='images/"+messages.otherAvatar+"low.jpg' alt='"+other+"' style='max-width:28px; max-height:28px;' onclick='getUser(this)' style='cursor: pointer;'/></span></div><div  class='DialogName' onclick='getUser(this)' style='display:inline-block; position:relative; top: -3px;  height:30px;'> "+other+"</div><span id='"+other+"' class='glyphicon glyphicon-remove  AnimateGlyph' style='position:absolute; left:845px; z-index:900;' onclick='RemoveDialogConfirmation(this)'></span></li><li class='list-group-item' style='min-height:50px; padding-top:1px;'><div id='DialogContainer' style='max-height:400px; overflow:auto;'><table id='DialogTable' style='width:100%;'><tbody>" + content;
                    content += "</tbody></table></div></li><li class='list-group-item' style='background:#cccccc;'>";
                    content += "<textarea id='TextArea' class='noscroll' name='text'>";
                    content += "</textarea><button name='"+other+"' onclick='AddMessage(this)' style='margin-left:94%; font-size: 0.8em; color: #999999;'>Send</button></li>";
                    
                    document.getElementById('Container').children[0].innerHTML = content;
                    document.getElementById('Container').style.display = 'block';
                    NoMoreElements = (messages.noMore === "true");
                    if (!NoMoreElements)
                        $('#DialogContainer').scroll(function(){
                                       if ((this.scrollTop <= 50)&&(!NoMoreElements)) {getOldMessages();}
                        });
                    var DialogContainer = document.getElementById("DialogContainer");
                    var DialogLi = DialogContainer.parentNode;
                    if (DialogContainer.scrollHeight >= 400) DialogLi.style.cssText+=' padding-right:1px;';
                    DialogContainer.scrollTop = DialogContainer.scrollHeight;
                    NumberOfUnreadDialogs();
                    ChangeTextAreaSize();
                    timerLastMessageId = setInterval(checkLastMessageId,1000);
                    

                    if  (messages.length > 0) if (messages[0].to_id===messages[0].user) setTimeout(ChangeUnread,2000);    
                };
                xhr.setRequestHeader("other",other);
                xhr.setRequestHeader("lastId", "-1");
                xhr.send('');
               }
            };
            
           function ChangeUnread(){
             var other = $('.list-group-item > div').first().next().html();
             if ($('#DialogTable table').last().css('float')==='right'){
               var xhr = new XMLHttpRequest();
                xhr.open('POST','changeunread',true);
                xhr.setRequestHeader("other",other);
                xhr.send('');
              ChangeDialogStyles();
            }
          };
          
          function ChangeDialogStyles(){
                var color = 13421772;
                var step = 1118481;
                var timer = setTimeout(function ChangeClass(){
                           color += step;
                         
                           var background = '#'+color.toString(16);
                           $('.unreadMessage').css('background',background);
                        
                           if (color.toString(16) < 'eeeeef') setTimeout(ChangeClass,30); 
                           else {$('.unreadMessage').each(function(){
                                                this.style.background = '#f8f8f8';
                                                this.className = 'notUnreadMessage';});
                                 $('.DialogTimeUnread').each(function(){
                                                this.className = 'DialogTime'; });
                           };
                },100);
          }
           
           function getOldMessages(){
               var other = $('.DialogName').first().html();
               var xhr = new XMLHttpRequest();
                xhr.open('GET','getoldmessages',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var other = xhr.getResponseHeader("other");
                    var messages = JSON.parse(xhr.responseText);
                    var content = "";
                    var dialogTable = document.getElementById('DialogTable');
                    NoMoreElements = (messages.noMore === "true");
                    var DialogContainer = document.getElementById("DialogContainer");
                    var position = DialogContainer.scrollHeight - DialogContainer.scrollTop;
                    for (var i = 0; i < messages.length; i++){
                       dialogTable.firstChild.innerHTML = "<tr><td>" + MessageToTable(messages[i])+"</td></tr>"+dialogTable.firstChild.innerHTML;
                      } 
                    DialogContainer.scrollTop = DialogContainer.scrollHeight - position;

                };
                xhr.setRequestHeader("other",other);
                var firstId = $('#DialogTable table').first().attr('id');
                firstId = firstId.substring(10,firstId.split('').length);
                xhr.setRequestHeader("lastId",firstId);
                xhr.send('');
            }    
           
           function getNewMessages(){
               clearTimeout(timerLastMessageId);
               var other = $('.DialogName').first().html();
               var xhr = new XMLHttpRequest();
                xhr.open('GET','getnewmessages',true);
                xhr.onreadystatechange = function(){
                    if (this.readyState !== 4) return;
                    var other = xhr.getResponseHeader("other");
                    var messages = JSON.parse(xhr.responseText);
                    var content = "";
                    var dialogTable = document.getElementById('DialogTable');
                    var count = messages.length - 1;
                    
                    while(count > -1){
                        var message = messages[count];
                       dialogTable.firstChild.innerHTML += "<tr style='opacity:0'><td>" + MessageToTable(message)+"</td></tr>";
                       count--;
                      }
                     var timer = setTimeout(function change(){(function ChangeOpacity(count){
                           var elements = $('#DialogTable > tbody').children();
                           var opacity = +elements.last().css('opacity');
                           opacity = (opacity * 10 + 1)/10;
                           for(var i = elements.size() - 1; i > elements.size() - count - 2; i-- ){
                              elements.get(i).style.opacity = opacity;
                            };
                           if (opacity < 1) setTimeout(change,30); 
                       })(messages.length - 1);},100);  
                    var DialogContainer = document.getElementById("DialogContainer");
                    DialogContainer.scrollTop = DialogContainer.scrollHeight;
                    var DialogLi = DialogContainer.parentNode;
                    if ((DialogContainer.scrollHeight >= 400) && (DialogLi.style.cssText.indexOf('padding-right') === -1)) DialogLi.style.cssText+=' padding-right:1px;';
                    
                    document.getElementById('TextArea').onfocus = function(){
                                    ChangeUnread(); 
                                    document.getElementById('TextArea').onfocus='';
                                };
                    timerLastMessageId = setInterval(checkLastMessageId,1000);
                };
                xhr.setRequestHeader("other",other);
                xhr.setRequestHeader("getOldMessages","false");
                var lastId = $('#DialogTable table').last().attr('id');
                lastId = (lastId === undefined) ? -1 : lastId.substring(10,lastId.split('').length);
                xhr.setRequestHeader("lastId",lastId);
                xhr.send('');
           }
           

           
            
            function cleanNavbar(){
               var navbar = document.getElementsByClassName('nav navbar-nav')[0];
               for (var i = 0; i < navbar.children.length; i++)
                 navbar.children[i].className = "";
            }
                
            function AddMessage(elem){
                var text = elem.previousSibling.value;
                text = cleanText(text.replace(/[\r\n]/g,':.!'));
                elem.previousSibling.value = '';
                var xhr = new XMLHttpRequest();
                xhr.open('POST','addmessage?value='+text,true);
                xhr.onreadystatechange = function() {
                    if (this.readyState !== 4) return;
                };
                xhr.setRequestHeader("other",elem.name);
                xhr.send('');
            };
            
            function cleanText(text){
                return text.replace(/[\\]/g, '\\\\')
                           .replace(/</g, "&lt;")
                           .replace(/>/g, "&gt;")
                           .replace(/"/g, "&quot;")
                           .replace(/'/g, "&#039;")
                           .replace(/&/g, "!:.");
            }
            
           
            function MessageToTable(message){
                
                var align = (message.from_id===message.user) ? "left" : "right";
                var strings = message.value.split(':.!');
                var unreadTime = message.unread==='true' ? "class='DialogTimeUnread'" : "class='DialogTime'";  
                var unread = message.unread==='true' ? "class='unreadMessage'" : "class='notUnreadMessage'";
                var table = "<table id='messageId_"+message.id+"' "+unread+"' style='float:"+align+"'>";
                var paddingFirst = (strings.length === 1) ? "padding:5px;" : "padding:5px 5px 0px 5px;";
                if (message.from_id===message.user) table += "<td style = '"+paddingFirst+"'>" + strings[0] + "</td><td rowspan='"+strings.length+"' valign='top' style='padding-top:4px'><i "+unreadTime+">"+getDate(message.date)+"</i></td></tr>";
                else table += "<td rowspan='"+strings.length+"' valign='top'  style='padding-top:4px'><i "+unreadTime+">"+getDate(message.date)+"</i></td><td style = '"+paddingFirst+"'>" + strings[0] + "</td></tr>";
                for (var i = 1; i < strings.length; i++){
                    var padding = (i === strings.length - 1) ? 'padding:0px 5px 5px 5px' : 'padding:0px 5px 0px 5px';
                    table +="<tr><td style = '"+padding+"'>" + strings[i] + "</td></tr>";
                   
                }
                table += "</table>";
                return table;
            };
            function CommentToTable(value){
                var strings = value.split(':.!'); 
                var table = "<table  style='float:left; font-size:0.8em; color:#555555;'>";
                for (var i = 0; i < strings.length; i++){
                    var padding = (i === strings.length - 1) ? 'padding:0px 5px 5px 5px' : 'padding:0px 5px 0px 5px';
                    table +="<tr><td style = '"+padding+"'>" + strings[i] + "</td></tr>";
                }
                table += "</table>";
                return table;
            };
            
            function ChangeTextAreaSize(){
                var elem = $('#TextArea');
                var hiddenDiv;
                if (!!document.getElementById('hiddenDiv')){
                    hiddenDiv = document.getElementById('hiddenDiv');
                } 
                else
                    hiddenDiv = $(document.createElement('div'));
                $('body').append(hiddenDiv);
                hiddenDiv = $('div').last();
                hiddenDiv.attr("id",'hiddenDiv');
                hiddenDiv.addClass('hiddenDiv');
                hiddenDiv = $('.hiddenDiv');
                hiddenDiv.css("width", elem.css("width"));
                elem.bind('keyup', function(){
                var text = elem.val().replace(/\n/g, '<br>');
                hiddenDiv.html(text + "a");
                elem.css('height',hiddenDiv.height() + 5);
            });
            };
            
            
         </script>
    </body>    
</html>    