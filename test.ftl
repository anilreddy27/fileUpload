<?xml version='1.0' encoding='UTF-8'?>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <style rel='stylesheet' type='text/css' media='print'>
        @page {
            size: 211mm 320mm;
            margin: 11mm;
        }

        * {
            font-family:arial, Arial Unicode MS, tahoma, helvetica, sans-serif;
            font-size: 10pt;
        }

        body {
            margin: 0 !important;
            padding: 0 !important;
        }

        table {
            width: 100%;
        }

        table, th, td {
            border-collapse: collapse;
        }

        .section {
            padding: 10px 0;
        }

        .title {
            font-weight: bold;
            font-size: 13pt;
            padding: 20px 0 10px 0;
        }

        .panel {
            border: 1px solid #eee;
        }

        .tableSupport{
            border: 1px solid #eee;
        }

        .panel-title {
            font-weight: bold;
            font-size: 11pt;
            background-color: #eee;
            padding: 5px;
        }

        .caption {
            font-weight: bold;
            width: 20%;
            padding: 5px;
        }

        .data {
            width: 30%;
            padding: 5px;
        }
        .captionInside {
            font-weight: bold;
            width: 60%;
            padding: 5px;
        }

        .dataInside {
            width: 70%;
            padding: 5px;
        }
        table {
            border-collapse: collapse;
        }

        .support {
            width: 100%;
            padding: 5px;
        }
        .captionSupport	{
            font-weight: bold;
            width: 0%;
        }
        SPAN.li  {display: list-item; margin-left: 0em}
    </style>
</head>
<body>
<table class='subpage'>
     <tr>
             <td class='caption'>Application Name</td>
             <td class='data'>Test</td>
             <td class='caption'>Asset ID</td>
             <td class='data'>Test</td>
     </tr>
     <tr>
             <td class='caption'>Asset Type</td>
             <td class='data'>Test</td>
             <td class='caption'>SOX Status</td>
             <td class='data'>Test</td>
     </tr>
     <tr>
             <td class='caption'>Account(s)</td>
             <td class='data'>Test</td>
             <td class='caption'>Environment</td>
             <td class='data'>Test</td>
     </tr>
     <tr>
             <td class='caption'>Tech Owner</td>
             <td class='data'>Test</td>
             <td class='caption'>VP Tech Owner</td>
             <td class='data'>Test</td>
     </tr>

</table>
<table class='subpage'>
     <tr>
         <td class='title'>Assessment Information </td>
    </tr>
    <tr>
            <td class='section'>
                <table class='panel'>
                    <tr>
                        <td class='panel-title' colspan='2'>Security</td>
                    </tr>
                     <tr>
                        <td style='padding-left:50px;' class='captionInside'>IAM Policies</td>
                    </tr>
                    <tr>
                        <td style='padding-left:100px;' class='captionInside'>Federated</td>
                    </tr>
                    <tr>
                        <td style='padding-left:150px;color:green;' class='captionInside'>Complaint = ${(federatedCount)!"0"}</td>
                    </tr>
                    <tr>
                        <td style='padding-left:150px;color:grey;' class='captionInside'>Review Required = 2</td>
                    </tr>
                    <tr>
                        <td style='padding-left:150px;color:red;' class='captionInside'>Non-Complaint = 5</td>
                    </tr>
                    <tr>
                        <td style='padding-left:100px;' class='captionInside'>Non Federated</td>
                    </tr>
                    <tr>
                        <td style='padding-left:150px;color:green;' class='captionInside'>Complaint = 6</td>
                    </tr>
                    <tr>
                        <td style='padding-left:150px;color:grey;' class='captionInside'>Review Required = 8</td>
                    </tr>
                    <tr>
                        <td style='padding-left:150px;color:red;' class='captionInside'>Non-Complaint = 8</td>
                    </tr>
                </table>
            </td>
    </tr>
    <tr>
                <td class='section'>
                    <table class='panel'>
                        <tr>
                            <td class='panel-title' colspan='2'>Testing</td>
                        </tr>
                         <#if s3Status == 'Complaint'>
                                <tr>
                                   <td  style='padding-left:150px;' class='captionInside'>S3-status(<span style='color:green;'>Complaint</span>)</td>
                                </tr>
                          </#if>
                        <#if s3Status == 'Non Complaint'>
                                <tr>
                                   <td style='padding-left:150px;' class='captionInside'>S3-status(<span style='color:red;'>Non Complaint</span>)</td>
                                </tr>
                         </#if>
                        <#if s3Status == 'N/A'>
                                <tr>
                                   <td style='padding-left:150px;' class='captionInside'><span style='color:grey;'>Non Complaint</span></td>
                                </tr>
                         </#if>

                    </table>
                </td>
        </tr>
</table>
</body>
</html>