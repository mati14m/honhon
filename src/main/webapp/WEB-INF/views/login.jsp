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
                <li><a href="#Log out">Log out<span class="ui_icon Log out"></span></a></li>
                <li><a href="#My books">My books<span class="ui_icon My books"></span></a></li>
                <li><a href="#Add book">Add book<span class="ui_icon Add book"></span></a></li>
                <li><a href="#Find book">Find book<span class="ui_icon Find book"></span></a></li>
            </ul>
        </div>
    </div>

    <div id="content_wrapper">
        <div id="content">

            <div class="scroll">
                <div class="scrollContainer">

                    <div class="panel" id="home">

                    </div> <!-- end of home -->

                    <div class="panel" id="Log out">
                        <h1>Log out</h1>

                    </div>

                    <div class="panel" id="My books">
                        <h1>My books</h1>

                    </div>

                    <div class="panel" id="Add book">
                        <h1>Add book</h1>
                        <form action="...">
                            Title:<input type="text" name="title" id="title"/>
                        </form>
                        <form action="...">
                            Author:<input type="text" name="author" id="author" />
                        </form>
                        <form action="...">
                            Publisher:<input type="text" name="publisher" id="publisher" />
                        </form>
                        <form action="...">
                            Comment:<input type="text" name="comment" id="comment"/>
                        </form>
                        <div>
                            <button class="btn btn-primary" id="save">Save</button>
                        </div>

                    </div>

                    <div class="panel" id="Find book">
                        <h1>Find book</h1>

                    </div>


                </div>
            </div>

            <!-- end of scroll -->

        </div> <!-- end of content -->
    </div> <!-- end of content_wrapper -->

</div> <!-- end of slider -->
<script type="text/javascript">
$("#save").click(function(){
    $.ajax({
        url: '/abc',
        type: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        contentType: 'application/json',
        data: JSON.stringify({
            title: document.getElementById("title").value,
            author: document.getElementById("author").value,
            publisher: document.getElementById("publisher").value,
            comment: document.getElementById("comment").value
        }),
        success: function(data) {
            alert('Load was performed.');
            document.getElementById("title").value = "";
            document.getElementById("author").value = "";
            document.getElementById("publisher").value = "";
            document.getElementById("comment").value = "";
        }
    });


});
</script>


</body>
</html>
