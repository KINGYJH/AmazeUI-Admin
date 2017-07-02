<%--
  Created by IntelliJ IDEA.
  User: King
  Date: 2017/6/30
  Time: 20:46
  网页侧边导航栏
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- 侧边导航栏 -->
<div region="west" title="在线演示" showHeader="true" bodyStyle="padding-left:1px;" showSplitIcon="true" width="230"
     minWidth="100" maxWidth="350">
    <ul id="demoTree" class="mini-tree" showTreeIcon="true" style="width:100%;height:100%;"
        enableHotTrack="true" onbeforeexpand="onBeforeExpand">
        <li><a href="overview.html" target="main">Overview</a></li>

        <li>
            <span expanded="true">快速入门</span>
            <ul>
                <li>
                    <span expanded="false">性能测试 <span style="color:Red;">New!</span></span>
                    <ul>
                        <li><a href="../BigTest/120-form.html" target="_blank">表单：120 控件 </a></li>
                        <li><a href="../BigTest/300-datagrid.html" target="_blank">表格：300 行 </a></li>
                        <li><a href="../BigTest/10000-datagrid.html" target="_blank">表格：10000 行 </a></li>
                        <li><a href="../BigTest/10000-tree.html" target="_blank">Tree：10000 节点 </a></li>
                        <li><a href="../BigTest/10000-treegrid.html" target="_blank">TreeGrid：10000 节点 </a></li>
                    </ul>

                </li>
                <li>
                    <span expanded="true">典型界面  </span>
                    <ul>
                        <li><a href="datagrid/pager.html" target="main">表单查询 </a></li>
                        <li><a href="datagrid/celledit.html" target="main">表格维护：快速</a></li>
                        <li><a href="datagrid/datagrid.html" target="main">表格维护：弹出</a></li>
                        <li><a href="datagrid/detailgrid.html" target="main">表格联动</a></li>
                        <li><a href="tree/detailgrid.html" target="main">树形联动</a></li>
                        <li><a href="tree/actiontree.html" target="main">树形维护 </a></li>
                        <li><a href="buttonedit/selectGrid.html" target="main">弹出选择：表格 </a></li>
                        <li><a href="buttonedit/selectTree.html" target="main">弹出选择：树 </a></li>
                        <!--    <li><a href="combobox/linkage-combo.html" target="main">下拉联动选择 </a></li>
                            <li><a href="listbox/moveitems.html" target="main">列表双向移动 </a></li>-->
                    </ul>
                </li>

                <li>
                    <span value="crud" expanded="false">CRUD</span>
                    <ul>
                        <li><a href="datagrid/celledit.html" target="main">单元格编辑 </a></li>
                        <li><a href="datagrid/rowedit.html" target="main">行编辑</a></li>
                        <li><a href="datagrid/editform.html" target="main">行内表单编辑</a></li>
                        <li><a href="datagrid/datagrid.html" target="main">弹出编辑（子页面）</a></li>
                        <li><a href="datagrid/popupeditform.html" target="main">弹出编辑（同页面）</a></li>
                        <li><a href="databinding/databinding.html" target="main">数据绑定 <span
                                style="color:Red;">New!</span></a></li>
                    </ul>
                </li>
                <li>
                    <span value="validator" expanded="false">数据验证</span>
                    <ul>
                        <li><a href="form/rules.html" target="main" title="验证规则">验证规则 <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="form/validation.html" target="main" title="数据验证">表单：验证</a></li>
                        <li><a href="form/validLabel.html" target="main" title="文本显示">表单：文本显示</a></li>
                        <li><a href="form/validGroup.html" target="main" title="组合验证">表单：组合验证</a></li>
                        <li><a href="form/validWindow.html" target="main" title="弹出框验证">表单：弹出框验证</a></li>

                    </ul>
                </li>
                <li value="layout">
                    <span expanded="false">页面布局</span>
                    <ul>
                        <li><a href="outlooktree/outlooktree.html" target="_blank">主框架：OutlookTree</a></li>
                        <li><a href="outlookmenu/outlookmenu.html" target="_blank">主框架：OutlookMenu</a></li>
                        <li><a href="tree/navtree.html" target="_blank">主框架：Tree</a></li>
                        <li><a href="menu/menubar.html" target="_blank">主框架：MenuBar</a></li>
                        <li><a href="layout/loginLayout.html" target="_blank">登录页面</a></li>
                        <li><a href="datagrid/celledit.html" target="main">CURD界面</a></li>
                        <li><a href="fit/fit.html" target="main">CURD撑满页面</a></li>
                        <li><a href="formlayout/formlayout.html" target="main">表单布局</a></li>
                    </ul>
                </li>
                <li>
                    <span value="master-detail" expanded="false">Master-Detail</span>
                    <ul>
                        <li><a href="tree/detailgrid.html" target="main">Tree-DataGrid</a></li>
                        <li><a href="datagrid/detailform.html" target="main">Detail Form</a></li>
                        <li><a href="datagrid/detailgrid.html" target="main">Detail Grid</a></li>
                        <li><a href="datagrid/detailtabs.html" target="main">Detail Tabs</a></li>
                        <li><a href="datagrid/inline_detailform.html" target="main">Inline Form</a></li>
                        <li><a href="datagrid/inline_detailgrid.html" target="main">Inline Grid</a></li>
                        <li><a href="datagrid/inline_detailtabs.html" target="main">Inline Tabs</a></li>
                        <li><a href="datagrid/popupeditform.html" target="main">Popup Form</a></li>
                    </ul>
                </li>
                <li>
                    <span value="openwindow" expanded="false">弹出面板 </span>
                    <ul>
                        <li><a href="datagrid/datagrid.html" target="main">弹出编辑面板</a></li>
                        <li><a href="buttonedit/selectGrid.html" target="main">弹出选择表格</a></li>
                        <li><a href="buttonedit/selectGrid_Multi.html" target="main">弹出多选表格</a></li>
                        <li><a href="buttonedit/selectGrid_Multi2.html" target="main">弹出多选表格（复杂）</a></li>
                        <li><a href="buttonedit/selectGrid_Multi3.html" target="main">弹出多选表格（跨页）</a></li>
                        <li><a href="buttonedit/selectTree.html" target="main">弹出选择树</a></li>
                        <li><a href="buttonedit/selectTree_Multi.html" target="main">弹出多选树</a></li>

                        <li><a href="buttonedit/selectGrid_js.html" target="main">弹出选择表格（JS）</a></li>
                        <li><a href="buttonedit/selectTree_js.html" target="main">弹出选择树（JS） </a></li>
                    </ul>
                </li>
            </ul>
        </li>
        <li>
            <span expanded="false">控件列表</span>
            <ul>
                <li><a href="messagebox.html" target="main">MessageBox</a></li>
                <li><a href="iconcls.html" target="main">iconCls</a></li>

                <li>
                    <span expanded="false">表单控件</span>
                    <ul>
                        <li><a href="progressbar/progressbar.html" target="main" title="进度条">ProgressBar <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="form/labelfield.html" target="main">LabelField <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="databinding/databinding.html" target="main">DataBinding <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="fontcss/fontcss.html" target="main" title="自定义字体大小">Diy FontSize <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="form/diysize.html" target="main" title="自定义控件高度">Diy Size <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="form/aslabel.html" target="main" title="文本显示输入框">AsLabel <span
                                style="color:Red;">New!</span></a></li>
                        <li>
                            <span value="form" expanded="false">Form</span>
                            <ul>
                                <li><a href="form/form.html" target="main" title="按钮">Form</a></li>
                                <li><a href="form/rules.html" target="main" title="验证规则">Validation Rules <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="form/validation.html" target="main" title="数据验证">Validation</a></li>
                                <li><a href="form/validLabel.html" target="main" title="文本显示">Validation Label</a>
                                </li>
                                <li><a href="form/validGroup.html" target="main" title="组合验证">Validation Summary</a>
                                </li>
                                <li><a href="form/validWindow.html" target="main" title="弹出框验证">Validation
                                    Window</a></li>
                            </ul>
                        </li>
                        <li>
                            <span value="button" expanded="false">Button</span>
                            <ul>
                                <li><a href="toolbar/toolbaroverflow.html" target="main" title="下拉弹出层工具栏">ToolBarOverflow<span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="button/button_iconfont.html" target="main" title="文字图标">文字图标<span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="button/button_img.html" target="main" title="图片图标">图片图标<span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="button/button_bootstrap.html" target="main" title="Bootstrap风格按钮">Button
                                    ( Bootstrap )<span style="color:Red;">New!</span></a></li>
                                <li><a href="button/button.html" target="main" title="按钮">Button</a></li>
                                <li><a href="button/menubutton.html" target="main" title="下拉菜单按钮">MenuButton</a>
                                </li>
                                <li><a href="button/radiobutton.html" target="main" title="按钮单选组">RadioButton</a>
                                </li>
                                <li><a href="toolbar/toolbar.html" target="main" title="工具栏按钮">Toolbar</a></li>
                            </ul>
                        </li>
                        <li>
                                <span value="tooltip" expanded="false">TooTtip <span
                                        style="color:Red;">New!</span></span>
                            <ul>
                                <li><a href="tooltip/tooltip.html" target="main" title="提示框">ToolTip</a></li>
                                <li><a href="tooltip/ajaxload.html" target="main" title="ajax加载提示">Ajax Load</a>
                                </li>
                                <li><a href="tooltip/grid.html" target="main" title="单元格加载提示">Grid Tooltip</a></li>
                                <li><a href="tooltip/form.html" target="main" title="表单验证错误提示">Form Tooltip</a></li>
                            </ul>
                        </li>
                        <li><a href="textboxlist/textboxlist.html" target="main" title="多选输入框">TextBoxList </a></li>
                        <li><a href="autocomplete/autocomplete.html" target="main" title="文本自动填充框">AutoComplete
                            <span style="color:Red;">New!</span></a></li>
                        <li><a href="treeselect/treeselect.html" target="main" title="树形下拉选择框">TreeSelect</a></li>
                        <li><a href="treeselect/lazy.html" target="main" title="懒加载树形下拉框">TreeSelect(Lazy) <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="combobox/combobox.html" target="main" title="下拉选择框">ComboBox</a></li>
                        <li><a href="combobox/linkage-combo.html" target="main"
                               title="联动选择ComboBox">ComboBox(联动选择)</a></li>
                        <li><a href="combobox/drawcell.html" target="main" title="下拉选择框">ComboBox(Draw item)</a>
                        </li>
                        <li><a href="datepicker/datepicker.html" target="main" title="日期选择框">DatePicker</a></li>
                        <li><a href="monthpicker/monthpicker.html" target="main" title="年月选择框">MonthPicker</a></li>
                        <li><a href="lookup/lookup.html" target="main" title="下拉搜索框">Lookup <span
                                style="color:Red;">New!</span></a></li>

                        <li value="checkbox"><a href="checkbox/checkbox.html" target="main" title="复选框">CheckBox</a>
                        </li>


                        <li><a href="listbox/listbox.html" target="main" title="列表框">ListBox</a></li>
                        <li><a href="listbox/moveitems.html" target="main" title="在两个列表框之间选中移动项">ListBox(Move
                            Items)</a></li>

                        <li><a href="checkboxlist/checkboxlist.html" target="main" title="多选框组">CheckBoxList</a>
                        </li>

                        <li><a href="radiobuttonlist/radiobuttonlist.html" target="main" title="单选框组">RadioButtonList</a>
                        </li>


                        <li><a href="calendar/calendar.html" target="main" title="日期选择器">Calendar</a></li>

                        <li><a href="buttonedit/buttonedit.html" target="main" title="按钮输入框">ButtonEdit</a></li>
                        <li><a href="buttonedit/buttonedit_buttons.html" target="main" title="自定义按钮">ButtonEdit
                            Buttons<span style="color:Red;">New!</span></a></li>
                        <li><a href="buttonedit/buttonedit_height.html" target="main" title="自定义输入框高度">ButtonEdit
                            Height<span style="color:Red;">New!</span></a></li>
                        <li><a href="filteredit/filteredit.html" target="main" title="过滤输入框">FilterEdit <span
                                style="color:Red;">New!</span></a></li>

                        <li><a href="textbox/textbox.html" target="main" title="单行输入框">TextBox</a></li>
                        <li><a href="textbox/textbox.html" target="main" title="密码输入框">Password</a></li>
                        <li><a href="textbox/textbox.html" target="main" title="多行输入框">TextArea</a></li>

                        <li><a href="spinner/spinner.html" target="main" title="数字输入框">Spinner</a></li>

                        <li><a href="timespinner/timespinner.html" target="main" title="时间输入框">TimeSpinner</a></li>

                        <li><a href="fileupload/htmlfile_ajax.html" target="main" title="Ajax文件上传选择框">AjaxFileUpload
                            <span style="color:Red;">New!</span></a></li>
                        <li><a href="fileupload/htmlfile.html" target="main" title="文件上传选择框(HTML版本)">HtmlFile</a>
                        </li>
                        <li><a href="fileupload/fileupload.html" target="main"
                               title="文件上传选择框(FLASH版本)">FileUpload</a></li>

                        <li><a href="multiupload/multiupload.html" target="main" title="多选文件上传">MultiUpload <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="multiupload/multiupload_openwindow.html" target="main" title="多选文件上传弹出框">MultiUpload
                            Window <span style="color:Red;">New!</span></a></li>

                    </ul>
                </li>
                <li>
                    <span expanded="false">表格控件</span>
                    <ul>
                        <li><a href="datagrid/jsonp.html" target="main">JsonP <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="datagrid/exceledit.html" target="main">Excel EditMode<span style="color:Red;">New!</span></a>
                        </li>
                        <li><a href="datagrid/copyexcel.html" target="main">Copy Excel <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="datagrid/datagrid.html" target="main">DataGrid</a></li>
                        <li><a href="datagrid/cellvalidation.html" target="main">CellValidation </a></li>
                        <li><a href="databinding/databinding.html" target="main">DataBinding </a></li>
                        <li><a href="datagrid/pager.html" target="main">Pagination</a></li>
                        <li><a href="datagrid/diypager.html" target="main">Diy Pagination </a></li>
                        <li><a href="datagrid/pager_client.html" target="main">Client Pagination </a></li>
                        <li><a href="datagrid/pagerbuttons.html" target="main">Pager Buttons </a></li>
                        <li><a href="datagrid/fitsize.html" target="_blank">Fit Size</a></li>
                        <li><a href="datagrid/export.html" target="main">Export Excel</a></li>
                        <li>
                            <span expanded="false">Grid Rows</span>
                            <ul>
                                <li><a href="datagrid/filter_client.html" target="main">Filter ( Client ) <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/filter.html" target="main">Filter ( Server )</a></li>
                                <li><a href="datagrid/summary.html" target="main">Data Summary</a></li>
                                <li><a href="datagrid/multiselect.html" target="main">Multi Select</a></li>
                                <li><a href="datagrid/columnsmenu.html" target="main">ColumnsMenu <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/diycolumnsmenu.html" target="main">Diy ColumnsMenu <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/contextmenu.html" target="main">ContextMenu</a></li>
                                <li><a href="datagrid/alternating.html" target="main">Alternating Row Style</a></li>
                                <li><a href="datagrid/gridlines.html" target="main">Grid Lines</a></li>
                                <li><a href="datagrid/rowdetail.html" target="main">Row Detail</a></li>
                                <!--<li><a href="datagrid/preview.html" target="main">Preview Row</a></li>-->
                                <li><a href="datagrid/rowtemplate.html" target="main">Row Template</a></li>
                                <li><a href="datagrid/moveitems.html" target="main">MoveItems </a></li>
                                <li><a href="datagrid/moveitems_copy.html" target="main">MoveItems Copy </a></li>
                                <li><a href="datagrid/moverow.html" target="main">Move Up/Down </a></li>
                            </ul>
                        </li>
                        <li>
                            <span expanded="false">Grid Columns</span>
                            <ul>
                                <li><a href="datagrid/setcolumns.html" target="main">Dynamic Columns <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/fixedcolumns.html" target="main">Fixed Columns</a></li>
                                <li><a href="datagrid/columngroup.html" target="main">Column Group</a></li>
                                <li><a href="datagrid/drawcell.html" target="main">Draw Cell</a></li>
                                <li><a href="datagrid/drawcell2.html" target="main">Draw Cell 2</a></li>
                                <li><a href="datagrid/currencyformatter.html" target="main">CurrencyFormatter <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/hidecolumn.html" target="main">Show/Hide Column</a></li>
                                <li><a href="datagrid/fixcolumnwidth.html" target="main">Fix Column Width</a></li>
                                <li><a href="datagrid/fitcolumnwidth.html" target="main">Fit Column Width</a></li>
                                <li><a href="datagrid/mergecells.html" target="main">MergeCells </a></li>
                                <li><a href="datagrid/mergecolumns.html" target="main">MergeColumns <span
                                        style="color:Red;">New!</span></a></li>
                            </ul>
                        </li>
                        <li>
                            <span expanded="false">Grid Edit</span>
                            <ul>
                                <li><a href="datagrid/celledit.html" target="main">CellEdit <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/rowedit.html" target="main">RowEdit</a></li>
                                <li><a href="datagrid/editform.html" target="main">EditForm</a></li>
                                <li><a href="datagrid/popupeditform.html" target="main">Popup EditForm</a></li>
                                <li><a href="datagrid/editable.html" target="main">Cell Editable <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/editable-rowedit.html" target="main">RowEdit Editable <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/celllinkedit.html" target="main">CellLinkEdit <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/celllinkedit2.html" target="main">RowLinkEdit <span
                                        style="color:Red;">New!</span></a></li>

                                <li><a href="datagrid/rowedit_lookup.html" target="main">Lookup Editor</a></li>
                                <li><a href="datagrid/rowedit_openwindow.html" target="main">OpenWindow Editor</a>
                                </li>
                                <li><a href="datagrid/rowedit_openpage.html" target="main">OpenPage Editor</a></li>
                                <li><a href="datagrid/rowedit_textboxlist.html" target="main">TextBoxList Editor</a>
                                </li>
                                <li><a href="datagrid/rowedit_autocomplete.html" target="main">AutoComplete
                                    Editor</a></li>
                                <li><a href="datagrid/rowedit_treeselect.html" target="main">TreeSelect Editor</a>
                                </li>
                                <li><a href="datagrid/staticedit.html" target="main">StaticEdit </a></li>

                            </ul>
                        </li>
                        <li>
                            <span expanded="false">Sort & Group <span style="color:Red;">New!</span></span>
                            <ul>
                                <li><a href="datagrid/sorting.html" target="main">Sorting (Server)</a></li>
                                <li><a href="datagrid/sorting_client.html" target="main">Sorting (Client) </a></li>
                                <li><a href="datagrid/sorting_multi.html" target="main">Multi Sorting <span
                                        style="color:Red;">New!</span></a></li>
                                <li><a href="datagrid/grouping.html" target="main">Grouping </a></li>
                            </ul>
                        </li>
                        <li>
                            <span expanded="false">Master-Detail</span>
                            <ul>
                                <li><a href="datagrid/detailform.html" target="main">Detail Form</a></li>
                                <li><a href="datagrid/detailgrid.html" target="main">Detail Grid</a></li>
                                <li><a href="datagrid/detailtabs.html" target="main">Detail Tabs</a></li>
                                <li><a href="datagrid/inline_detailform.html" target="main">Inline Form</a></li>
                                <li><a href="datagrid/inline_detailgrid.html" target="main">Inline Grid</a></li>
                                <li><a href="datagrid/inline_detailtabs.html" target="main">Inline Tabs</a></li>
                                <li><a href="datagrid/popupeditform.html" target="main">Popup Form</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li>
                    <span expanded="false">树控件</span>
                    <ul>
                        <li><a href="tree/tree.html" target="main">Tree</a></li>
                        <li><a href="tree/list2tree.html" target="main">List to Tree</a></li>
                        <li><a href="tree/createtree.html" target="main">Create Tree</a></li>
                        <li><a href="tree/loadtree.html" target="main">Load Tree <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="tree/lazytree.html" target="main">Lazy Tree</a></li>
                        <li><a href="tree/checkmodel.html" target="main">CheckModel <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="tree/checkboxtree.html" target="main">CheckBox Tree</a></li>
                        <li><a href="tree/functiontree.html" target="main">Function Tree<span style="color:Red;">New!</span></a>
                        </li>
                        <li><a href="tree/selection.html" target="main">Selection</a></li>
                        <li><a href="tree/filtertree.html" target="main">FilterTree(client) </a></li>
                        <li><a href="tree/filterServer.html" target="main">FilterTree(server) <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="tree/contextmenu.html" target="main">ContextMenu</a></li>
                        <li><a href="tree/navtree.html" target="main">Navigation Tree</a></li>
                        <li><a href="tree/actiontree.html" target="main">Add/Remove/Update Node</a></li>
                        <li><a href="tree/collapsetree.html" target="main">Expand/Collapse</a></li>
                        <li><a href="tree/drawnode.html" target="main">Draw Node</a></li>
                        <li><a href="tree/treeimg.html" target="main">Node Image <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="tree/movenode.html" target="main">MoveNode Window</a></li>
                        <li><a href="tree/dragdrop.html" target="main">DragDrop Node</a></li>
                        <li><a href="tree/dragdrop2.html" target="main">GiveFeedback</a></li>
                        <li><a href="tree/between.html" target="main">Between Tree <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="tree/autocollapse.html" target="main">AutoCollapse</a></li>
                        <li><a href="tree/treeCRUD.html" target="main">TreeCRUD <span style="color:Red;">New!</span></a>
                        </li>
                        <li><a href="tree/treeCRUD2.html" target="main">TreeCRUD2 <span
                                style="color:Red;">New!</span></a></li>
                    </ul>
                </li>
                <li>
                    <span expanded="false">TreeGrid <span style="color:Red;">New!</span></span>
                    <ul>
                        <li><a href="treegrid/treegrid.html" target="main">TreeGrid </a></li>
                        <li><a href="treegrid/treesort.html" target="main">Tree Sorting <span style="color:Red;">New!</span></a>
                        </li>
                        <li><a href="treegrid/celledit.html" target="main">CellEdit</a></li>
                        <li><a href="treegrid/drawcell.html" target="main">DrawCell</a></li>
                        <li><a href="treegrid/lazytree.html" target="main">LazyLoad</a></li>
                        <li><a href="treegrid/dragdrop.html" target="main">DragDrop</a></li>
                        <li><a href="treegrid/checkboxtree.html" target="main">CheckBox TreeGrid</a></li>
                        <li><a href="treegrid/functiontree.html" target="main">Function TreeGrid <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="pagertree/pagertree.html" target="main">PagerTree <span
                                style="color:Red;">New!</span></a></li>
                    </ul>
                </li>
                <li>
                    <span expanded="false">布局控件</span>
                    <ul>
                        <li><a href="layout/grid.html" target="main">网格布局 <span style="color:Red;">New!</span></a>
                        </li>
                        <li><a href="fit/fit.html" target="main">Fit Layout </a></li>
                        <li><a href="panel/panel.html" target="main">Panel</a></li>
                        <li><a href="panel/panel_bootstrap.html" target="main" title="Bootstrap风格面板">Panel (
                            Bootstrap ) <span style="color:Red;">New!</span></a></li>
                        <li><a href="panel/load.html" target="main">Panel（LazyLoad）</a></li>
                        <li><a href="panel/panel-max.html" target="main">Panel（Max）</a></li>
                        <li><a href="window/window.html" target="main">Window <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="splitter/splitter.html" target="main">Splitter</a></li>
                        <li><a href="layout/layout.html" target="main">Layout</a></li>
                        <li><a href="formLayout/formlayout.html" target="main">Form Layout</a></li>
                        <li><a href="fieldset/fieldset.html" target="main">FieldSet</a></li>

                    </ul>
                </li>
                <li>
                    <span expanded="false">导航控件</span>
                    <ul>

                        <li><a href="toolbar/toolbar.html" target="main">Toolbar</a></li>
                        <li><a href="toolbar/toolbar2.htm" target="main" title="工具栏面板">Toolbar2 <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="toolbar/toolbaroverflow.html" target="main"
                               title="下拉弹出层工具栏">ToolBarOverflow<span style="color:Red;">New!</span></a></li>
                        <li><a href="tabs/tabs.html" target="main">Tabs</a></li>
                        <li><a href="tabs/tabs2.html" target="main">Tabs (Add/Remove)</a></li>
                        <li><a href="tabs/load.html" target="main" title="Tabs（动态页面）">Tabs（动态页面）</a></li>
                        <li><a href="tabs/tabsLayout.html" target="_blank">Tabs Layout </a></li>
                        <li><a href="tabs/contextmenu.html" target="main">Tabs ContextMenu </a></li>
                        <li><a href="tabs/buttons.html" target="main">Tabs Buttons <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="menu/menu.html" target="main">Menu</a></li>
                        <li><a href="menu/menubar.html" target="main">MenuBar (Load)</a></li>
                        <li><a href="menu/contextmenu.html" target="main">ContextMenu</a></li>
                        <li><a href="datagrid/contextmenu.html" target="main">ContextMenu（DataGrid）</a></li>
                        <li><a href="tree/contextmenu.html" target="main">ContextMenu（TreeGrid）</a></li>
                        <li><a href="menu/menuimg.html" target="main">MenuImg <span
                                style="color:Red;">New!</span></a></li>
                        <li><a href="tree/navtree.html" target="_blank">Tree</a></li>
                        <li><a href="pager/pager.html" target="main">Pager</a></li>
                        <li><a href="outlookbar/outlookbar.html" target="main">OutlookBar</a></li>
                        <li><a href="outlookmenu/outlookmenu.html" target="_blank">OutlookMenu</a></li>
                        <li><a href="outlooktree/outlooktree.html" target="_blank">OutlookTree</a></li>
                    </ul>
                </li>
                <li>
                    <span expanded="false">其他控件</span>
                    <ul>
                        <li>
                            <span expanded="false">富文本编辑器</span>
                            <ul>
                                <li><a href="kindeditor/kindeditor.html" target="main">KindEditor</a></li>
                                <li><a href="ckeditor/ckeditor.html" target="main">CKEditor</a></li>
                            </ul>
                        </li>
                        <li>
                            <span expanded="false">图表</span>
                            <ul>
                                <li>
                                    <span expanded="false">Highcharts</span>
                                    <ul>
                                        <li><a href="highcharts/overview.html" target="main">Overview</a></li>
                                        <li><a href="highcharts/linechart.html" target="main" title="LineChart">LineChart</a>
                                        </li>
                                        <li><a href="highcharts/barchart.html" target="main" title="BarChart">BarChart</a>
                                        </li>
                                        <li><a href="highcharts/columnchart.html" target="main" title="ColumnChart">ColumnChart</a>
                                        </li>
                                        <li><a href="highcharts/piechart.html" target="main" title="PieChart">PieChart</a>
                                        </li>
                                        <li><a href="highcharts/areachart.html" target="main" title="areachart">AreaChart</a>
                                        </li>
                                        <li><a href="http://www.highcharts.com/demo/" target="_blank">更多示例...</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <span expanded="false">Open Flash Chart</span>
                                    <ul>
                                        <li><a href="open-flash-chart/overview.html" target="main"
                                               title="LineChart">Overview</a></li>
                                        <li><a href="open-flash-chart/lineChart.html" target="main"
                                               title="LineChart">LineChart</a></li>
                                        <li><a href="open-flash-chart/barChart.html" target="main" title="BarChart">BarChart</a>
                                        </li>
                                        <li><a href="open-flash-chart/barChart3D.html" target="main"
                                               title="BarChart 3D">BarChart 3D</a></li>
                                        <li><a href="open-flash-chart/columnChart.html" target="main"
                                               title="ColumnChart">ColumnChart</a></li>
                                        <li><a href="open-flash-chart/pieChart.html" target="main" title="PieChart">PieChart</a>
                                        </li>
                                        <li><a href="open-flash-chart/radarChart.html" target="main"
                                               title="RadarChart">RadarChart</a></li>
                                        <li><a href="open-flash-chart/candleChart.html" target="main"
                                               title="CandleChart">CandleChart</a></li>
                                        <li><a href="open-flash-chart/scatterChart.html" target="main"
                                               title="ScatterChart">ScatterChart</a></li>
                                        <li><a href="open-flash-chart/StackedBarChart.html" target="main"
                                               title="Stacked Bar Chart">Stacked Bar Chart</a></li>
                                        <li><a href="open-flash-chart/tags.html" target="main" title="Tags">Tags</a>
                                        </li>
                                        <li><a href="open-flash-chart/combinedChart.html" target="main"
                                               title="Combined Chart">Combined Chart</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <span expanded="false">YUI Chart</span>
                                    <ul>
                                        <li><a href="YUI2/overview.html" target="main" title="Overview">Overview</a>
                                        </li>
                                        <li><a href="YUI2/lineChart.html" target="main"
                                               title="LineChart">LineChart</a></li>
                                        <li><a href="YUI2/barChart.html" target="main" title="BarChart">BarChart</a>
                                        </li>
                                        <li><a href="YUI2/columnChart.html" target="main" title="ColumnChart">ColumnChart</a>
                                        </li>
                                        <li><a href="YUI2/pieChart.html" target="main" title="PieChart">PieChart</a>
                                        </li>
                                        <li><a href="YUI2/DualAxesChart.html" target="main" title="Dual Axes Chart">Dual
                                            Axes Chart</a></li>
                                        <li><a href="YUI2/StackedBarChart.html" target="main"
                                               title="Stacked Bar Chart">Stacked Bar Chart</a></li>
                                        <li><a href="YUI2/CustomizeSeriesItems.html" target="main"
                                               title="Customize series items">Customize series items</a></li>
                                        <li><a href="YUI2/CustomSkin.html" target="main" title="Custom skin">Custom
                                            skin</a></li>
                                        <li><a href="YUI2/StylingLines,BordersAndFills.html" target="main"
                                               title="Styling Lines, Borders and Fills">Styling Lines, Borders and
                                            Fills</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li><a href="desktop/index.html" target="_blank">DeskTop 桌面应用 </a></li>
                        <li><a href="portal/index.html" target="main">Portal 门户 </a></li>
                        <li><a href="filemanager/index.html" target="main">文档管理器</a></li>
                    </ul>
                </li>

            </ul>
        </li>

    </ul>
</div>