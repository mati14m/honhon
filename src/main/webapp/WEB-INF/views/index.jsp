<%@ page import="org.pac4j.springframework.security.authentication.ClientAuthenticationToken" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="org.pac4j.core.profile.UserProfile" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Wall Shelf - Free CSS Template</title>
<meta name="keywords" content="wall shelf, free css templates, one page, full site, CSS, HTML" />
<meta name="description" content="Wall Shelf is a free CSS template provided by templatemo.com" />
<link href="/resources/templatemo_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/resources/css/coda-slider.css" type="text/css" media="screen" title="no title" charset="utf-8" />

<script src="/resources/js/jquery-1.2.6.js" type="text/javascript"></script>
<script src="/resources/js/jquery.scrollTo-1.3.3.js" type="text/javascript"></script>
<script src="/resources/js/jquery.localscroll-1.2.5.js" type="text/javascript" charset="utf-8"></script>
<script src="/resources/js/jquery.serialScroll-1.2.1.js" type="text/javascript" charset="utf-8"></script>
<script src="/resources/js/coda-slider.js" type="text/javascript" charset="utf-8"></script>
<script src="/resources/js/jquery.easing.1.3.js" type="text/javascript" charset="utf-8"></script>

</head>
<body>
	
    <div id="slider">
    	
        <div id="header_wrapper">
            <div id="header">
                <a href="#home"></a>
            </div>
		</div>
        
        <div id="menu_wrapper">
            <div id="menu">
                <ul class="navigation">
                    <li><a href="#home" class="selected">Home<span class="ui_icon home"></span></a></li>
                    <li><a href="#Log in">Log in<span class="ui_icon aboutus"></span></a></li>
                </ul>
            </div>
		</div>
                        
		<div id="content_wrapper">
        <div id="content">
        
            <div class="scroll">
                <div class="scrollContainer">
                
                    <div class="panel" id="home">

                    </div> <!-- end of home -->
                    
                    <div class="panel" id="Log in">
                        <h1>Log in</h1>
                        <a href="google/index.jsp">Zaloguj sie przez Google</a><br />
                        <br /><br />
                        <%Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
                            UserProfile profile = null;
                            if (auth != null && auth instanceof ClientAuthenticationToken) {
                                ClientAuthenticationToken token = (ClientAuthenticationToken) auth;
                                profile = token.getUserProfile();
                            }
                        %>
                        profile : <%=profile%>
                    </div>

                    
                </div>
            </div>
            
            <!-- end of scroll -->
        
        </div> <!-- end of content -->
        </div> <!-- end of content_wrapper -->
    
    </div> <!-- end of slider -->

</body>
</html>
