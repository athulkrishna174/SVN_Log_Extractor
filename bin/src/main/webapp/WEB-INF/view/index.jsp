<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <title>Log Extractor</title>
</head>
<body>
    <h1 id="mainHeading">SVN COMMIT HISTORY EXTRACTOR</h1>
    <div id="parentDiv">
        <div class="formDivClass">
            <form action="/extract" method="post" modelAttribute="extractor" enctype="multipart/form-data" onsubmit="return validateExtractForm();">
                <div id="pathDiv">
                	<table>
                		<tr>
                            <td>
                                <label for="filePath" class="form-label" style="margin-bottom: 0;font-weight: bolder;">Input File :</label>
                            </td>
                            <td>
                                <input type="file" id="file" name="file" class="inputField form-control form-control-sm" required="required">
                            </td>
                        </tr>
                	</table>
                </div>

                <div id="outFormatDiv">
                    <table>
                        <tr>
                            <td>
                                <label for="outFormat" id="outputFormatHeading" class="heading h6">Output File Format:</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="radio" id="txtFormat" name="outputFileFormat" value=".txt" checked="checked">
                                <label class="form-check-label" for="txtFormat">.txt</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="radio" id="csvFormat" name="outputFileFormat" value=".csv">
                                <label class="form-check-label" for="csvFormat">.csv</label>
                            </td>
                        </tr>
                    </table>
                </div>

                <div id="projectsDiv">
                    <p class="heading h6">Select Projects:</p>

                    <table>
                        <tr>
                            <td>
                                <input class="form-check-input" type="radio" id="projectMymt" name="selectedProject" value="MYMT" checked="checked">
                                <label class="form-check-label" for="projectMymt">MYMT</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="radio" id="projectApplicationEAR" name="selectedProject" value="ApplicationEAR">
                                <label class="form-check-label" for="projectApplicationEAR">ApplicationEAR</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="radio" id="projectDataAccessTier" name="selectedProject" value="DataAccessTier">
                                <label class="form-check-label" for="projectDataAccessTier">DataAccessTier</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="radio" id="projectBusinessTier" name="selectedProject" value="BusinessTier">
                                <label class="form-check-label" for="projectBusinessTier">BusinessTier</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="radio" id="projectBusinessComponentClient" name="selectedProject" value="BusinessComponentClient">
                                <label class="form-check-label" for="projectBusinessComponentClient">BusinessComponentClient</label>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <input class="form-check-input" type="radio" id="projectMymtProfile" name="selectedProject" value="MymtProfile">
                                <label class="form-check-label" for="projectMymtProfile">MymtProfile</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="radio" id="projectMsgProcessEJB" name="selectedProject" value="MsgProcessEJB">
                                <label class="form-check-label" for="projectMsgProcessEJB">MsgProcessEJB</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="radio" id="projectDataBaseScript" name="selectedProject" value="DataBaseScript">
                                <label class="form-check-label" for="projectDataBaseScript">DataBaseScript</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="radio" id="projectPresentationTier" name="selectedProject" value="PresentationTier">
                                <label class="form-check-label" for="projectPresentationTier">PresentationTier</label>
                            </td>
                            <!-- <td>
                                <input class="form-check-input" type="checkbox" id="projectOther" name="selectedProject" value="otherProject" onChange="enableDisableOtherInput(this, 'otherProject')">
                                <label class="form-check-label" for="projectOther">Other:</label>
                                <input type="text" id="otherProject" name="otherProject" disabled="true" class="otherInput form-control form-control-sm">
                            </td> -->
                        </tr>
                    </table>
                </div>

                <div id="optionsDiv">
                    <p class="heading h6">Select Options:</p>

                    <table>
                        <tr>
                        	<td>
                                <input class="form-check-input" type="checkbox" id="optionSortFile" name="optionList" value="SORT_FILE" checked="true">
                                <label class="form-check-label" for="optionSortFile">Sort File</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="checkbox" id="optionRemoveDuple" name="optionList" value="REMOVE_DUPLICATE">
                                <label class="form-check-label" for="optionRemoveDuple">Remove Duplicate</label>
                            </td>
                            <td>
                                <input class="form-check-input" type="checkbox" id="optionRemoveUnwanted" name="optionList" value="REMOVE_UNWNTED" onChange="hideUnHideBlock(this, 'requiredFormatDiv')">
                                <label class="form-check-label" for="optionRemoveUnwanted">Remove Unwanted Lines</label>
                            </td>
                        </tr>
                    </table>

                    <div id="requiredFormatDiv" style="display:none;">
                        <p class="heading h6">Select Required Formats:</p>

                        <table>
                            <tr>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredJava" name="requiredFormats" value=".java">
                                    <label class="form-check-label" for="requiredJava">.java</label>
                                </td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredJsp" name="requiredFormats" value=".jsp">
                                    <label class="form-check-label" for="requiredJsp">.jsp</label>
                                </td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredJs" name="requiredFormats" value=".js">
                                    <label class="form-check-label" for="requiredJs">.js</label>
                                </td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredJar" name="requiredFormats" value=".jar">
                                    <label class="form-check-label" for="requiredJar">.jar</label>
                                </td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredVm" name="requiredFormats" value=".vm">
                                    <label class="form-check-label" for="requiredVm">.vm</label>
                                </td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredSql" name="requiredFormats" value=".sql">
                                    <label class="form-check-label" for="requiredSql">.sql</label>
                                </td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredXml" name="requiredFormats" value=".xml">
                                    <label class="form-check-label" for="requiredXml">.xml</label>
                                </td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredCss" name="requiredFormats" value=".css">
                                    <label class="form-check-label" for="requiredCss">.css</label>
                                </td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredCsv" name="requiredFormats" value=".csv">
                                    <label class="form-check-label" for="requiredCsv">.csv</label>
                                </td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="requiredProperties" name="requiredFormats" value=".properties">
                                    <label for="requiredProperties">.properties</label>
                                </td>
                            </tr>

                            <!-- <tr>
                                <td colspan="10">
                                    <input class="form-check-input" type="checkbox" id="needOther" name="needOther" value="otherFormat" onChange="enableDisableOtherInput(this, 'otherNeededFormat')">
                                    <label for="needOther">Other:</label>
                                    <input type="text" id="otherNeededFormat" name="otherNeededFormat" disabled="true" class="otherInput form-control form-control-sm">
                                </td>
                            </tr> -->
                        </table>

                    </div>
                </div>
                <Button class="btn btn-primary" type="submit" id="extractBtn" value="Extract">Extract</Button>
                <!-- <Button class="btn btn-primary" type="reset" id="extractBtn" value="Extract">Reset</Button> -->
            </form>
        </div>
    </div>

    <script src="/js/script.js"></script>
</body>
</html>