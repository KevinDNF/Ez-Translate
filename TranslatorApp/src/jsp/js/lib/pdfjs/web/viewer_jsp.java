package jsp.js.lib.pdfjs.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;

public class viewer_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    javax.servlet.jsp.PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n<!--\nCopyright 2012 Mozilla Foundation\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n    http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.\n\nAdobe CMap resources are covered by their own copyright but the same license:\n\n    Copyright 1990-2015 Adobe Systems Incorporated.\n\nSee https://github.com/adobe-type-tools/cmap-resources\n-->\n<html dir=\"ltr\" mozdisallowselectionprint>\n  <head>\n    <meta charset=\"utf-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\n");
      out.write("    <meta name=\"google\" content=\"notranslate\">\n    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n    <title>PDF.js viewer</title>\n\n\n    <link rel=\"stylesheet\" href=\"viewer.css\">\n\n\n<!-- This snippet is used in production (included from viewer.html) -->\n<link rel=\"resource\" type=\"application/l10n\" href=\"locale/locale.properties\">\n<script src=\"../build/pdf.js\"></script>\n\n\n    <script src=\"viewer.js\"></script>\n\n  </head>\n\n  <body tabindex=\"1\" class=\"loadingInProgress\">\n    <div id=\"outerContainer\">\n\n      <div id=\"sidebarContainer\">\n        <div id=\"toolbarSidebar\">\n          <div class=\"splitToolbarButton toggled\">\n            <button id=\"viewThumbnail\" class=\"toolbarButton toggled\" title=\"Show Thumbnails\" tabindex=\"2\" data-l10n-id=\"thumbs\">\n               <span data-l10n-id=\"thumbs_label\">Thumbnails</span>\n            </button>\n            <button id=\"viewOutline\" class=\"toolbarButton\" title=\"Show Document Outline (double-click to expand/collapse all items)\" tabindex=\"3\" data-l10n-id=\"document_outline\">\n");
      out.write("               <span data-l10n-id=\"document_outline_label\">Document Outline</span>\n            </button>\n            <button id=\"viewAttachments\" class=\"toolbarButton\" title=\"Show Attachments\" tabindex=\"4\" data-l10n-id=\"attachments\">\n               <span data-l10n-id=\"attachments_label\">Attachments</span>\n            </button>\n          </div>\n        </div>\n        <div id=\"sidebarContent\">\n          <div id=\"thumbnailView\">\n          </div>\n          <div id=\"outlineView\" class=\"hidden\">\n          </div>\n          <div id=\"attachmentsView\" class=\"hidden\">\n          </div>\n        </div>\n        <div id=\"sidebarResizer\" class=\"hidden\"></div>\n      </div>  <!-- sidebarContainer -->\n\n      <div id=\"mainContainer\">\n        <div class=\"findbar hidden doorHanger\" id=\"findbar\">\n          <div id=\"findbarInputContainer\">\n            <input id=\"findInput\" class=\"toolbarField\" title=\"Find\" placeholder=\"Find in documentâ¦\" tabindex=\"91\" data-l10n-id=\"find_input\">\n            <div class=\"splitToolbarButton\">\n              <button id=\"findPrevious\" class=\"toolbarButton findPrevious\" title=\"Find the previous occurrence of the phrase\" tabindex=\"92\" data-l10n-id=\"find_previous\">\n");
      out.write("                <span data-l10n-id=\"find_previous_label\">Previous</span>\n              </button>\n              <div class=\"splitToolbarButtonSeparator\"></div>\n              <button id=\"findNext\" class=\"toolbarButton findNext\" title=\"Find the next occurrence of the phrase\" tabindex=\"93\" data-l10n-id=\"find_next\">\n                <span data-l10n-id=\"find_next_label\">Next</span>\n              </button>\n            </div>\n          </div>\n\n          <div id=\"findbarOptionsContainer\">\n            <input type=\"checkbox\" id=\"findHighlightAll\" class=\"toolbarField\" tabindex=\"94\">\n            <label for=\"findHighlightAll\" class=\"toolbarLabel\" data-l10n-id=\"find_highlight\">Highlight all</label>\n            <input type=\"checkbox\" id=\"findMatchCase\" class=\"toolbarField\" tabindex=\"95\">\n            <label for=\"findMatchCase\" class=\"toolbarLabel\" data-l10n-id=\"find_match_case_label\">Match case</label>\n            <span id=\"findResultsCount\" class=\"toolbarLabel hidden\"></span>\n          </div>\n\n          <div id=\"findbarMessageContainer\">\n");
      out.write("            <span id=\"findMsg\" class=\"toolbarLabel\"></span>\n          </div>\n        </div>  <!-- findbar -->\n\n        <div id=\"secondaryToolbar\" class=\"secondaryToolbar hidden doorHangerRight\">\n          <div id=\"secondaryToolbarButtonContainer\">\n            <button id=\"secondaryPresentationMode\" class=\"secondaryToolbarButton presentationMode visibleLargeView\" title=\"Switch to Presentation Mode\" tabindex=\"51\" data-l10n-id=\"presentation_mode\">\n              <span data-l10n-id=\"presentation_mode_label\">Presentation Mode</span>\n            </button>\n\n            <button id=\"secondaryOpenFile\" class=\"secondaryToolbarButton openFile visibleLargeView\" title=\"Open File\" tabindex=\"52\" data-l10n-id=\"open_file\">\n              <span data-l10n-id=\"open_file_label\">Open</span>\n            </button>\n\n            <button id=\"secondaryPrint\" class=\"secondaryToolbarButton print visibleMediumView\" title=\"Print\" tabindex=\"53\" data-l10n-id=\"print\">\n              <span data-l10n-id=\"print_label\">Print</span>\n            </button>\n");
      out.write("\n            <button id=\"secondaryDownload\" class=\"secondaryToolbarButton download visibleMediumView\" title=\"Download\" tabindex=\"54\" data-l10n-id=\"download\">\n              <span data-l10n-id=\"download_label\">Download</span>\n            </button>\n\n            <a href=\"#\" id=\"secondaryViewBookmark\" class=\"secondaryToolbarButton bookmark visibleSmallView\" title=\"Current view (copy or open in new window)\" tabindex=\"55\" data-l10n-id=\"bookmark\">\n              <span data-l10n-id=\"bookmark_label\">Current View</span>\n            </a>\n\n            <div class=\"horizontalToolbarSeparator visibleLargeView\"></div>\n\n            <button id=\"firstPage\" class=\"secondaryToolbarButton firstPage\" title=\"Go to First Page\" tabindex=\"56\" data-l10n-id=\"first_page\">\n              <span data-l10n-id=\"first_page_label\">Go to First Page</span>\n            </button>\n            <button id=\"lastPage\" class=\"secondaryToolbarButton lastPage\" title=\"Go to Last Page\" tabindex=\"57\" data-l10n-id=\"last_page\">\n              <span data-l10n-id=\"last_page_label\">Go to Last Page</span>\n");
      out.write("            </button>\n\n            <div class=\"horizontalToolbarSeparator\"></div>\n\n            <button id=\"pageRotateCw\" class=\"secondaryToolbarButton rotateCw\" title=\"Rotate Clockwise\" tabindex=\"58\" data-l10n-id=\"page_rotate_cw\">\n              <span data-l10n-id=\"page_rotate_cw_label\">Rotate Clockwise</span>\n            </button>\n            <button id=\"pageRotateCcw\" class=\"secondaryToolbarButton rotateCcw\" title=\"Rotate Counterclockwise\" tabindex=\"59\" data-l10n-id=\"page_rotate_ccw\">\n              <span data-l10n-id=\"page_rotate_ccw_label\">Rotate Counterclockwise</span>\n            </button>\n\n            <div class=\"horizontalToolbarSeparator\"></div>\n\n            <button id=\"cursorSelectTool\" class=\"secondaryToolbarButton selectTool toggled\" title=\"Enable Text Selection Tool\" tabindex=\"60\" data-l10n-id=\"cursor_text_select_tool\">\n              <span data-l10n-id=\"cursor_text_select_tool_label\">Text Selection Tool</span>\n            </button>\n            <button id=\"cursorHandTool\" class=\"secondaryToolbarButton handTool\" title=\"Enable Hand Tool\" tabindex=\"61\" data-l10n-id=\"cursor_hand_tool\">\n");
      out.write("              <span data-l10n-id=\"cursor_hand_tool_label\">Hand Tool</span>\n            </button>\n\n            <div class=\"horizontalToolbarSeparator\"></div>\n\n            <button id=\"documentProperties\" class=\"secondaryToolbarButton documentProperties\" title=\"Document Propertiesâ¦\" tabindex=\"62\" data-l10n-id=\"document_properties\">\n              <span data-l10n-id=\"document_properties_label\">Document Propertiesâ¦</span>\n            </button>\n          </div>\n        </div>  <!-- secondaryToolbar -->\n\n        <div class=\"toolbar\">\n          <div id=\"toolbarContainer\">\n            <div id=\"toolbarViewer\">\n              <div id=\"toolbarViewerLeft\">\n                <button id=\"sidebarToggle\" class=\"toolbarButton\" title=\"Toggle Sidebar\" tabindex=\"11\" data-l10n-id=\"toggle_sidebar\">\n                  <span data-l10n-id=\"toggle_sidebar_label\">Toggle Sidebar</span>\n                </button>\n                <div class=\"toolbarButtonSpacer\"></div>\n                <button id=\"viewFind\" class=\"toolbarButton\" title=\"Find in Document\" tabindex=\"12\" data-l10n-id=\"findbar\">\n");
      out.write("                  <span data-l10n-id=\"findbar_label\">Find</span>\n                </button>\n                <div class=\"splitToolbarButton hiddenSmallView\">\n                  <button class=\"toolbarButton pageUp\" title=\"Previous Page\" id=\"previous\" tabindex=\"13\" data-l10n-id=\"previous\">\n                    <span data-l10n-id=\"previous_label\">Previous</span>\n                  </button>\n                  <div class=\"splitToolbarButtonSeparator\"></div>\n                  <button class=\"toolbarButton pageDown\" title=\"Next Page\" id=\"next\" tabindex=\"14\" data-l10n-id=\"next\">\n                    <span data-l10n-id=\"next_label\">Next</span>\n                  </button>\n                </div>\n                <input type=\"number\" id=\"pageNumber\" class=\"toolbarField pageNumber\" title=\"Page\" value=\"1\" size=\"4\" min=\"1\" tabindex=\"15\" data-l10n-id=\"page\">\n                <span id=\"numPages\" class=\"toolbarLabel\"></span>\n              </div>\n              <div id=\"toolbarViewerRight\">\n                <button id=\"presentationMode\" class=\"toolbarButton presentationMode hiddenLargeView\" title=\"Switch to Presentation Mode\" tabindex=\"31\" data-l10n-id=\"presentation_mode\">\n");
      out.write("                  <span data-l10n-id=\"presentation_mode_label\">Presentation Mode</span>\n                </button>\n\n                <button id=\"openFile\" class=\"toolbarButton openFile hiddenLargeView\" title=\"Open File\" tabindex=\"32\" data-l10n-id=\"open_file\">\n                  <span data-l10n-id=\"open_file_label\">Open</span>\n                </button>\n\n                <button id=\"print\" class=\"toolbarButton print hiddenMediumView\" title=\"Print\" tabindex=\"33\" data-l10n-id=\"print\">\n                  <span data-l10n-id=\"print_label\">Print</span>\n                </button>\n\n                <button id=\"download\" class=\"toolbarButton download hiddenMediumView\" title=\"Download\" tabindex=\"34\" data-l10n-id=\"download\">\n                  <span data-l10n-id=\"download_label\">Download</span>\n                </button>\n                <a href=\"#\" id=\"viewBookmark\" class=\"toolbarButton bookmark hiddenSmallView\" title=\"Current view (copy or open in new window)\" tabindex=\"35\" data-l10n-id=\"bookmark\">\n                  <span data-l10n-id=\"bookmark_label\">Current View</span>\n");
      out.write("                </a>\n\n                <div class=\"verticalToolbarSeparator hiddenSmallView\"></div>\n\n                <button id=\"secondaryToolbarToggle\" class=\"toolbarButton\" title=\"Tools\" tabindex=\"36\" data-l10n-id=\"tools\">\n                  <span data-l10n-id=\"tools_label\">Tools</span>\n                </button>\n              </div>\n              <div id=\"toolbarViewerMiddle\">\n                <div class=\"splitToolbarButton\">\n                  <button id=\"zoomOut\" class=\"toolbarButton zoomOut\" title=\"Zoom Out\" tabindex=\"21\" data-l10n-id=\"zoom_out\">\n                    <span data-l10n-id=\"zoom_out_label\">Zoom Out</span>\n                  </button>\n                  <div class=\"splitToolbarButtonSeparator\"></div>\n                  <button id=\"zoomIn\" class=\"toolbarButton zoomIn\" title=\"Zoom In\" tabindex=\"22\" data-l10n-id=\"zoom_in\">\n                    <span data-l10n-id=\"zoom_in_label\">Zoom In</span>\n                   </button>\n                </div>\n                <span id=\"scaleSelectContainer\" class=\"dropdownToolbarButton\">\n");
      out.write("                  <select id=\"scaleSelect\" title=\"Zoom\" tabindex=\"23\" data-l10n-id=\"zoom\">\n                    <option id=\"pageAutoOption\" title=\"\" value=\"auto\" selected=\"selected\" data-l10n-id=\"page_scale_auto\">Automatic Zoom</option>\n                    <option id=\"pageActualOption\" title=\"\" value=\"page-actual\" data-l10n-id=\"page_scale_actual\">Actual Size</option>\n                    <option id=\"pageFitOption\" title=\"\" value=\"page-fit\" data-l10n-id=\"page_scale_fit\">Page Fit</option>\n                    <option id=\"pageWidthOption\" title=\"\" value=\"page-width\" data-l10n-id=\"page_scale_width\">Page Width</option>\n                    <option id=\"customScaleOption\" title=\"\" value=\"custom\" disabled=\"disabled\" hidden=\"true\"></option>\n                    <option title=\"\" value=\"0.5\" data-l10n-id=\"page_scale_percent\" data-l10n-args='{ \"scale\": 50 }'>50%</option>\n                    <option title=\"\" value=\"0.75\" data-l10n-id=\"page_scale_percent\" data-l10n-args='{ \"scale\": 75 }'>75%</option>\n                    <option title=\"\" value=\"1\" data-l10n-id=\"page_scale_percent\" data-l10n-args='{ \"scale\": 100 }'>100%</option>\n");
      out.write("                    <option title=\"\" value=\"1.25\" data-l10n-id=\"page_scale_percent\" data-l10n-args='{ \"scale\": 125 }'>125%</option>\n                    <option title=\"\" value=\"1.5\" data-l10n-id=\"page_scale_percent\" data-l10n-args='{ \"scale\": 150 }'>150%</option>\n                    <option title=\"\" value=\"2\" data-l10n-id=\"page_scale_percent\" data-l10n-args='{ \"scale\": 200 }'>200%</option>\n                    <option title=\"\" value=\"3\" data-l10n-id=\"page_scale_percent\" data-l10n-args='{ \"scale\": 300 }'>300%</option>\n                    <option title=\"\" value=\"4\" data-l10n-id=\"page_scale_percent\" data-l10n-args='{ \"scale\": 400 }'>400%</option>\n                  </select>\n                </span>\n              </div>\n            </div>\n            <div id=\"loadingBar\">\n              <div class=\"progress\">\n                <div class=\"glimmer\">\n                </div>\n              </div>\n            </div>\n          </div>\n        </div>\n\n        <menu type=\"context\" id=\"viewerContextMenu\">\n          <menuitem id=\"contextFirstPage\" label=\"First Page\"\n");
      out.write("                    data-l10n-id=\"first_page\"></menuitem>\n          <menuitem id=\"contextLastPage\" label=\"Last Page\"\n                    data-l10n-id=\"last_page\"></menuitem>\n          <menuitem id=\"contextPageRotateCw\" label=\"Rotate Clockwise\"\n                    data-l10n-id=\"page_rotate_cw\"></menuitem>\n          <menuitem id=\"contextPageRotateCcw\" label=\"Rotate Counter-Clockwise\"\n                    data-l10n-id=\"page_rotate_ccw\"></menuitem>\n        </menu>\n\n        <div id=\"viewerContainer\" tabindex=\"0\">\n          <div id=\"viewer\" class=\"pdfViewer\"></div>\n        </div>\n\n        <div id=\"errorWrapper\" hidden='true'>\n          <div id=\"errorMessageLeft\">\n            <span id=\"errorMessage\"></span>\n            <button id=\"errorShowMore\" data-l10n-id=\"error_more_info\">\n              More Information\n            </button>\n            <button id=\"errorShowLess\" data-l10n-id=\"error_less_info\" hidden='true'>\n              Less Information\n            </button>\n          </div>\n          <div id=\"errorMessageRight\">\n");
      out.write("            <button id=\"errorClose\" data-l10n-id=\"error_close\">\n              Close\n            </button>\n          </div>\n          <div class=\"clearBoth\"></div>\n          <textarea id=\"errorMoreInfo\" hidden='true' readonly=\"readonly\"></textarea>\n        </div>\n      </div> <!-- mainContainer -->\n\n      <div id=\"overlayContainer\" class=\"hidden\">\n        <div id=\"passwordOverlay\" class=\"container hidden\">\n          <div class=\"dialog\">\n            <div class=\"row\">\n              <p id=\"passwordText\" data-l10n-id=\"password_label\">Enter the password to open this PDF file:</p>\n            </div>\n            <div class=\"row\">\n              <input type=\"password\" id=\"password\" class=\"toolbarField\">\n            </div>\n            <div class=\"buttonRow\">\n              <button id=\"passwordCancel\" class=\"overlayButton\"><span data-l10n-id=\"password_cancel\">Cancel</span></button>\n              <button id=\"passwordSubmit\" class=\"overlayButton\"><span data-l10n-id=\"password_ok\">OK</span></button>\n            </div>\n          </div>\n");
      out.write("        </div>\n        <div id=\"documentPropertiesOverlay\" class=\"container hidden\">\n          <div class=\"dialog\">\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_file_name\">File name:</span> <p id=\"fileNameField\">-</p>\n            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_file_size\">File size:</span> <p id=\"fileSizeField\">-</p>\n            </div>\n            <div class=\"separator\"></div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_title\">Title:</span> <p id=\"titleField\">-</p>\n            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_author\">Author:</span> <p id=\"authorField\">-</p>\n            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_subject\">Subject:</span> <p id=\"subjectField\">-</p>\n            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_keywords\">Keywords:</span> <p id=\"keywordsField\">-</p>\n");
      out.write("            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_creation_date\">Creation Date:</span> <p id=\"creationDateField\">-</p>\n            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_modification_date\">Modification Date:</span> <p id=\"modificationDateField\">-</p>\n            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_creator\">Creator:</span> <p id=\"creatorField\">-</p>\n            </div>\n            <div class=\"separator\"></div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_producer\">PDF Producer:</span> <p id=\"producerField\">-</p>\n            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_version\">PDF Version:</span> <p id=\"versionField\">-</p>\n            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_page_count\">Page Count:</span> <p id=\"pageCountField\">-</p>\n");
      out.write("            </div>\n            <div class=\"row\">\n              <span data-l10n-id=\"document_properties_page_size\">Page Size:</span> <p id=\"pageSizeField\">-</p>\n            </div>\n            <div class=\"buttonRow\">\n              <button id=\"documentPropertiesClose\" class=\"overlayButton\"><span data-l10n-id=\"document_properties_close\">Close</span></button>\n            </div>\n          </div>\n        </div>\n        <div id=\"printServiceOverlay\" class=\"container hidden\">\n          <div class=\"dialog\">\n            <div class=\"row\">\n              <span data-l10n-id=\"print_progress_message\">Preparing document for printingâ¦</span>\n            </div>\n            <div class=\"row\">\n              <progress value=\"0\" max=\"100\"></progress>\n              <span data-l10n-id=\"print_progress_percent\" data-l10n-args='{ \"progress\": 0 }' class=\"relative-progress\">0%</span>\n            </div>\n            <div class=\"buttonRow\">\n              <button id=\"printCancel\" class=\"overlayButton\"><span data-l10n-id=\"print_progress_close\">Cancel</span></button>\n");
      out.write("            </div>\n          </div>\n        </div>\n      </div>  <!-- overlayContainer -->\n\n    </div> <!-- outerContainer -->\n    <div id=\"printContainer\"></div>\n  </body>\n</html>\n\n");
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }
}
