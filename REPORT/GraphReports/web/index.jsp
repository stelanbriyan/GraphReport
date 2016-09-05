<%-- 
    Document   : index
    Created on : Aug 31, 2016, 10:29:27 AM
    Author     : Stelan Briyan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.0.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" >
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.0/css/bootstrap-select.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.11.0/js/bootstrap-select.min.js"></script>

        <link href="CSS/style.css" rel="stylesheet" type="text/css"/>
        <script src="JS/canvasjs.min.js" type="text/javascript"></script>
        <script src="JS/script.js" type="text/javascript"></script>
        <title>GRAPH REPORT</title>
    </head>
    <body>
        <div class="navbar-fixed-top">
            <div class="top-bar">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            TYPE :
                            <select class="selectpicker1" id="typelist" multiple>
                                <!--                                <option value="'Residential'">Residential</option>
                                                                <option value="'X-RITE'">X-RITE</option>
                                                                <option value="'ATLAS'">ATLAS</option>-->
                            </select>
                            &nbsp;&nbsp;&nbsp;
                            MONTHS :
                            <select class="selectpicker" id="monthList" multiple>
                                <option value="'January'" selected>January</option>
                                <option value="'February'" selected>February</option>
                                <option value="'March'" selected>March</option>
                                <option value="'April'" selected>April</option>
                                <option value="'May'" selected>May</option>
                                <option value="'June'" selected>June</option>
                                <option value="'July'" selected>July</option>
                                <option value="'August'" selected>August</option>
                                <option value="'September'" selected>September</option>
                                <option value="'October'" selected>October</option>
                                <option value="'November'" selected>November</option>
                                <option value="'December'" selected>December</option>
                            </select>
                            <select class="selectpicker" id="yearList" multiple>
                                <option selected>2016</option>
                            </select>
                            <button id="search" class="btn btn-default">Search</button>
                            <img style="float: right;height: 40px;margin-right: 20px" src="IMG/logo_1.jpg" alt=""/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <br/>
        <br/>
        <br/>
        <br/>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12">
                    <center>
                        <span class="head1">AREA CHART</span><br/>
                        <span class="head2"> Interested in cumulative totals over a period of time or range of values.</span>
                    </center><br/>
                    <div class="chart">
                        <div id="chartContainer1"></div>
                    </div>            
                </div>
            </div>
            <br/>
            <br/>
            <div class="row">
                <div class="col-md-6">
                    <center>
                        <span class="head1">PIE CHART</span><br/>
                        <!--<span class="head2">The folks over at MaxCDN graciously provide CDN support for Bootstrap's CSS and JavaScript. Just use these Bootstrap CDN links.</span>-->
                    </center><br/>
                    <div class="chart">
                        <div id="chartContainer2"></div>
                    </div>            
                </div>
                <div class="col-md-6">
                    <center>
                        <span class="head1">BAR CHART</span><br/>
                        <!--<span class="head2">The folks over at MaxCDN graciously provide CDN support for Bootstrap's CSS and JavaScript. Just use these Bootstrap CDN links.</span>-->
                    </center><br/>
                    <div class="chart">
                        <div id="chartContainer3"></div>
                    </div>            
                </div>
            </div>
            <br/>
            <br/>
            <br/>
            <div class="row">
                <div class="col-md-12">
                    <center>
                        <span class="head1">COLUMN CHART</span><br/>
                        <!--<span class="head2">The folks over at MaxCDN graciously provide CDN support for Bootstrap's CSS and JavaScript. Just use these Bootstrap CDN links.</span>-->
                    </center><br/>
                    <div class="chart">
                        <div id="chartContainer4"></div>
                    </div>            
                </div>
            </div>
            <br/>
            <br/>
            <div class="row">
                <div class="col-md-12">
                    <center>
                        <span class="head1">LINE CHART</span><br/>
                    </center><br/>
                    <div class="chart">
                        <div id="chartContainer5"></div>
                    </div>            
                </div>
            </div>


        </div>
        <br/>
        <br/>
        <div class="bottom_of_report">
            <center>GRAPH SALES REPORT @ 2016  </center>
        </div>

    </body>
</html>
