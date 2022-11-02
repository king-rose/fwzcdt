<template>
	<div class="app-container">
		<el-form v-show="showSearch" ref="queryForm" :model="queryParams">
			<div class="search">
				<div style="width: 10%">
					<span class="mr20" style="font-size: 20px;font-weight: bold;height: 20px;line-height: 60px;color: #70798d;">标准元数据查询</span>
				</div>
				<div style="width: 6%">
					<el-form-item style="padding: 10px;margin-top: 2px" class="btnStyle">
						<span style="font-size: 16px"  :class="queryParams.search ? btnStyleFont:''" type="text" @click="queryParams.search=false">模糊查询</span>
					</el-form-item>
				</div>
				<div style="width: 2%;">
					<el-form-item style="padding-top: 10px;margin-top: 4px">
						<svg-icon style="font-size: 24px" :icon-class="queryParams.search ? 'right':'left'"></svg-icon>
					</el-form-item>
				</div>
				<div style="width: 6%">
					<el-form-item style="padding: 10px;margin-top: 2px" class="btnStyle">
						<span style="font-size: 16px" :class="!queryParams.search ? btnStyleFont:''" type="text" @click="queryParams.search=true">精确查询</span>
					</el-form-item>
				</div>

				<div style="width: 70%">
					<el-form-item style="padding: 10px" size="middle">
						<el-input
							style="width: 100%;"
							v-model="queryParams.keywords"
							placeholder="如字段名称、数据类型"
							clearable
							size="small"
							@keyup.enter.native="handleQuery"
						>
							<el-button slot="append" icon="el-icon-search" @click="handleQuery"></el-button>
						</el-input>
					</el-form-item>
				</div>
			</div>
		</el-form>

		<el-row :gutter="10" class="mb8">
			<el-col :span="1.5">
				<el-button
					icon="el-icon-plus"
					plain
					size="mini"
					type="primary"
					@click="handleAdd"
				>新增
				</el-button>
			</el-col>
			<el-col :span="1.5">
				<el-button class='filter-item' icon='el-icon-download' size="mini" type='primary' @click='daoRu'>
					导入
				</el-button>
			</el-col>
			<right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
		</el-row>

		<el-table v-loading="loading" :data="postList" @selection-change="handleSelectionChange">
			<el-table-column align="center" type="selection" width="55"/>
			<el-table-column align="center" label="字段名称" prop="stdDictKey" width="300"/>
			<el-table-column align="center" label="字段描述" prop="stdNameCn" width="450"/>
			<el-table-column :formatter="dataTypeFormat" align="center" label="数据类型" prop="stdDictType"/>
			<el-table-column :formatter="lengthTypeFormat" align="center" label="长度类型" prop="stdDictLengthType"/>
			<el-table-column align="center" label="字段长度(BYTE)" prop="stdDictLength"/>
			<el-table-column align="center" label="数据格式" prop="stdDictFormat"/>
			<el-table-column align="center" label="业务含义" prop="stdDictDesc" width="180"/>
		</el-table>

		<pagination
			v-show="total>0"
			:limit.sync="queryParams.pageSize"
			:page.sync="queryParams.pageNum"
			:total="total"
			@pagination="getList"
		/>

		<!-- 添加或修改岗位对话框 -->
		<el-dialog :title="title" :visible.sync="open" append-to-body width="800px">
			<el-form ref="form" :model="form" :rules="rules" label-width="200px">
				<el-row>
					<el-col :span="12">
						<el-form-item label="数据分类" prop="stdDictKeyGroup">
							<el-select v-model="form.stdDictKeyGroup" placeholder="请选择">
								<el-option
									v-for="dict in dataClassOptions"
									:key="dict.dictValue"
									:label="dict.dictLabel"
									:value="dict.dictValue"
								></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="标准类型" prop="stdType">
							<el-select v-model="form.stdType" placeholder="请选择">
								<el-option
									v-for="dict in stdTypeOptions"
									:key="dict.dictValue"
									:label="dict.dictLabel"
									:value="dict.dictValue"
								></el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="字段名称" prop="stdDictKey">
							<el-input v-model="form.stdDictKey"/>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="字段描述" prop="stdNameCn">
							<el-input v-model="form.stdNameCn"/>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="数据类型" prop="stdDictType">
							<el-select v-model="form.stdDictType" placeholder="请选择">
								<el-option
									v-for="dict in dataTypeOptions"
									:key="dict.dictValue"
									:label="dict.dictLabel"
									:value="dict.dictValue"
								></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="长度类型" prop="stdDictLengthType">
							<el-select v-model="form.stdDictLengthType" placeholder="请选择">
								<el-option
									v-for="dict in lengthTypeOptions"
									:key="dict.dictValue"
									:label="dict.dictLabel"
									:value="dict.dictValue"
								></el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="字段长度（BYTE）" prop="stdDictLength">
							<el-input v-model="form.stdDictLength"/>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="数据格式" prop="stdDictFormat">
							<el-input v-model="form.stdDictFormat"/>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="数标信息项编号" prop="stdCode">
							<el-input v-model="form.stdCode"/>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="业务含义" prop="stdDictDesc">
							<el-input v-model="form.stdDictDesc"/>
						</el-form-item>
					</el-col>
				</el-row>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button type="primary" @click="submitForm">确 定</el-button>
				<el-button @click="cancel">取 消</el-button>
			</div>
		</el-dialog>
		<!-- 导入对话框 -->
		<el-dialog :title="title1" :visible.sync="open1" append-to-body width="800px">
			<a class="file" href="javascript:;">
				<input id="upload" accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" type="file"
					   @change="importfxx(this)"/>
			</a>
			<div id="demo"></div>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="open1 = false">取消</el-button>
				<el-button type="primary" @click.native="submit">提交</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import {
	listDataSource,
	getDataSource,
	addDataSource,
	updateDataSource,
	delDataSource,
	foreachAddDataSource
} from "@/api/dataDict/dataSource";
import ScrollPane from '@/layout/components/TagsView/ScrollPane'

export default {
	name: "Post",
	components: {ScrollPane},
	dicts: ['sys_normal_disable'],
	data() {
		return {
			//样式
			btnStyleFont:'btnStyle_font',

			excelImportShow: false,  //导入文件的弹框
			uploadArr: [],   // 上传给后台的数据
			realname: '',  // 以下都是我要转换的数据，因人而异
			gender: '',
			age: '',
			minzu: '',
			wenhua: '',
			shengfenzheng: '',
			job: '',
			mobile: '',
			mark: '',
			dataList: [],
			dataClassOptions: [],
			stdTypeOptions: [],
			sjlxList: [],
			cdlxList: [],
			//长度类型
			lengthTypeOptions: [],
			//数据类型类型
			dataTypeOptions: [],
			value1: true,
			value2: true,
			// 遮罩层
			loading: true,
			// 选中数组
			ids: [],
			// 非单个禁用
			single: true,
			// 非多个禁用
			multiple: true,
			// 显示搜索条件
			showSearch: true,
			// 总条数
			total: 0,
			// 岗位表格数据
			postList: [],
			// 弹出层标题
			title: "",
			title1: "",


			// 是否显示弹出层
			open: false,
			open1: false,
			// 查询参数
			queryParams: {
				search:false,
				pageNum: 1,
				pageSize: 10,
				keywords:''
			},
			// 表单参数
			form: {},
			// 表单校验
			rules: {
				postName: [
					{required: true, message: "岗位名称不能为空", trigger: "blur"}
				],
				postCode: [
					{required: true, message: "岗位编码不能为空", trigger: "blur"}
				],
				postSort: [
					{required: true, message: "岗位顺序不能为空", trigger: "blur"}
				]
			}
		};
	},
	created() {
		this.getList();
		this.getDicts("length_type").then(response => {
			this.lengthTypeOptions = response.data;
		});
		this.getDicts("data_type").then(response => {
			this.dataTypeOptions = response.data;
		});
		this.getDicts("dict_key_group").then(response=>{
			this.dataClassOptions = response.data;
		})
		this.getDicts("data_stand_type").then(response=>{
			this.stdTypeOptions = response.data;
		})
	},
	methods: {
		//随机数
		randomString(e) {
			e = e || 32;
			var t = "abcdefhijkmnprstwxyz-123456789",
				a = t.length,
				n = "";
			for (var i = 0; i < e; i++) {
				n = n + t.charAt(Math.floor(Math.random() * a))
			}
			;
			return n
		},
		// 导入
		importfxx(obj) {
			let _this = this;
			let inputDOM = this.$refs.inputer;
			// 通过DOM取文件数据
			this.file = event.currentTarget.files[0];
			var rABS = false; //是否将文件读取为二进制字符串
			var f = this.file;
			var reader = new FileReader();
			FileReader.prototype.readAsBinaryString = function (f) {
				var binary = "";
				var rABS = false; //是否将文件读取为二进制字符串
				var pt = this;
				var wb; //读取完成的数据
				var outdata;
				var reader = new FileReader();
				reader.onload = function (e) {
					var bytes = new Uint8Array(reader.result);
					var length = bytes.byteLength;
					for (var i = 0; i < length; i++) {
						binary += String.fromCharCode(bytes[i]);
					}
					var XLSX = require('xlsx');
					console.log(XLSX)
					if (rABS) {
						wb = XLSX.read(btoa(fixdata(binary)), { //手动转化
							type: 'base64'
						});
					} else {
						wb = XLSX.read(binary, {
							type: 'binary'
						});
					}
					outdata = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[0]]); //outdata就是你想要的东西
					this.da = [...outdata]
					let arr = []
					let nameArr = outdata[1]
					for (let v in nameArr) {
						// 拿到 excel 里面的列名，根据列名得到里面的键名
						let title = nameArr[v]
						switch (title) {
							case '姓名':
								console.log('这是姓名', nameArr[v]);
								_this.realname = v
								console.log('这是realname', v);
								break;
							case '性别':
								console.log('这是性别', nameArr[v]);
								_this.gender = v
								console.log('这是gender', v);
								break;
							case '年龄':
								console.log('这是年龄', nameArr[v]);
								_this.age = v
								console.log('这是age', v);
								break;
							case '民族':
								console.log('这是民族', nameArr[v]);
								_this.minzu = v
								console.log('这是minzu', v);
								break;
							case '学历':
								console.log('这是性别', nameArr[v]);
								_this.wenhua = v
								console.log('这是job', v);
								break;
							case '身份证号':
								console.log('这是身份证号', nameArr[v]);
								_this.shengfenzheng = v
								console.log('这是shenfengzheng', v);
								break;
							case '工作单位/学校/社区':
								console.log('"工作单位/学校/社区"', nameArr[v]);
								_this.job = v
								console.log('这是job', v);
								break;
							case '手机/电话':
								console.log('这是手机/电话', nameArr[v]);
								_this.mobile = v
								console.log('这是mobile', v);
								break;
							case '备注':
								console.log('这是备注', nameArr[v]);
								_this.mark = v
								console.log('这是mark', v);
								break;
							default:
								break;
						}
					}
					let obj
					let uploadArr = []
					// 这里是一个excel里面会有多个sheets，把里面的恶数据整个到这个数组里面一起上传
					let leng = Object.keys(wb.Sheets).length
					console.log("wb")
					console.log(wb)
					console.log("wb.Sheets")
					console.log(wb.Sheets)
					console.log("Object.keys(wb.Sheets)")
					console.log(Object.keys(wb.Sheets))
					// 用for循环，leng 是sheets的个数，用来循环次数
					for (var i = 0; i < leng + 1; i++) {
						XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[i]])
						let arrr = XLSX.utils.sheet_to_json(wb.Sheets[wb.SheetNames[i]])
						arrr = arrr.slice(0)        //如果从第三行开始是数据，那这里就是1，如果从第四行开始是数据，那这里就是2
						uploadArr.push.apply(uploadArr, arrr)
						_this.uploadArr = uploadArr
					}
					let arrs = []
					_this.uploadArr.map(v => {
						let obj = {}
						obj.stdDictId = _this.randomString()
						obj.stdDictKey = v.字段名称 == undefined ? null : v.字段名称
						obj.stdNameCn = v.字段描述 == undefined ? null : v.字段描述
						obj.stdDictType = v.数据类型 == undefined ? null : v.数据类型
						obj.stdDictLengthType = v.长度类型 == undefined ? null : v.长度类型
						obj.stdDictLength = v.字段长度 == undefined ? null : v.字段长度
						obj.stdDictFormat = v.数据格式 == undefined ? null : v.数据格式
						obj.stdDictDesc = v.业务含义 == undefined ? null : v.业务含义
						arrs.push(obj)
					})
					_this.dataList = [...arrs];
					console.log("拿到 excel 里面的列名，根据列名得到里面的键名")
					console.log(_this.dataList)
					this.da.map(v => {
						let obj = {}
						obj.id = v.id
						obj.status = v.status
						arr.push(obj)
					})
					let para = {
						withList: arr
					}
					_this.$message({
						message: '请耐心等待导入成功',
						type: 'success'
					});
				}
				reader.readAsArrayBuffer(f);
			}
			if (rABS) {
				reader.readAsArrayBuffer(f);
			} else {
				reader.readAsBinaryString(f);
			}
		},
		// 确认导入
		submit() {
			let uploadArr = this.dataList
			let list = [];
			this.excelImportShow = false
			let obj
			for (let v in uploadArr) {
				obj = {
					stdDictKey:uploadArr[v].stdDictKey,
					stdDictId:uploadArr[v].stdDictId,
					stdDictDesc: uploadArr[v].stdDictDesc,
					stdDictFormat:uploadArr[v].stdDictFormat,
					stdDictType: uploadArr[v].stdDictType == 'String' ? '1' : '2',
					stdDictLength:uploadArr[v].stdDictLength,
					stdDictLengthType:uploadArr[v].stdDictLengthType == '最长' ? '1' : '0',
					stdNameCn:uploadArr[v].stdNameCn,
				}
				list.push(obj)
			}
			foreachAddDataSource(list).then(res=>{
				this.msgSuccess("新增成功")
				this.getList();
				this.open1 = false;
			})
		},
		// 长度类型字典翻译
		lengthTypeFormat(row, column) {
			return this.selectDictLabel(this.lengthTypeOptions, row.stdDictLengthType);
		},
		// 数据类型字典翻译
		dataTypeFormat(row, column) {
			return this.selectDictLabel(this.dataTypeOptions, row.stdDictType);
		},
		/** 查询列表 */
		getList() {
			this.loading = true;
			listDataSource(this.queryParams).then(response => {
				this.postList = response.rows;
				this.total = response.total;
				this.loading = false;
			});

		},
		// 取消按钮
		cancel() {
			this.open = false;
			this.open1 = false;
			this.reset();
		},
		// 表单重置
		reset() {
			this.form = {
				postId: undefined,
				postCode: undefined,
				postName: undefined,
				postSort: 0,
				status: "0",
				remark: undefined
			};
			this.resetForm("form");
		},
		/** 搜索按钮操作 */
		handleQuery() {
			this.queryParams.pageNum = 1;
			this.getList();
		},
		/** 重置按钮操作 */
		resetQuery() {
			this.resetForm("queryForm");
			this.handleQuery();
		},
		// 多选框选中数据
		handleSelectionChange(selection) {
			this.ids = selection.map(item => item.postId)
			this.single = selection.length != 1
			this.multiple = !selection.length
		},
		/** 新增按钮操作 */
		handleAdd() {
			this.reset();
			this.open = true;
			this.title = "标准元数据字典新增";
		},
		daoRu() {
			this.open1 = true;
			this.title1 = "标准元数据批量导入";
		},
		/** 修改按钮操作 */
		handleUpdate(row) {
			this.reset();
			const postId = row.postId || this.ids
			getPost(postId).then(response => {
				this.form = response.data;
				this.open = true;
				this.title = "修改岗位";
			});
		},
		/** 提交按钮 */
		submitForm() {
			this.$refs["form"].validate(valid => {
				if (valid) {
					if (this.form.stdDictId != null) {
						updateDataSource(this.form).then(response => {
							this.msgSuccess("修改成功");
							this.open = false;
							this.getList();
						});
					} else {
						addDataSource(this.form).then(response => {
							this.msgSuccess("新增成功");
							this.open = false;
							this.getList();
						});
					}
				}
			});
		},
		/** 批量新增 */
		submitForms() {
			if (this.dataList.length == 0) {
				alert("请选择文件")
			} else {
				foreachAddDataSource(this.dataList).then(response => {
					this.msgSuccess("批量新增成功");
					this.open1 = false;
					this.getList();
				});
			}
		},

		/** 删除按钮操作 */
		handleDelete(row) {
			const postIds = row.postId || this.ids;
			this.$modal.confirm('是否确认删除岗位编号为"' + postIds + '"的数据项？').then(function () {
				return delPost(postIds);
			}).then(() => {
				this.getList();
				this.$modal.msgSuccess("删除成功");
			}).catch(() => {
			});
		},
		/** 导出按钮操作 */
		handleExport() {
			this.download('system/post/export', {
				...this.queryParams
			}, `post_${new Date().getTime()}.xlsx`)
		}
	}
};
</script>
<style>
	.btnStyle_font{
		color: #70798d;
	}
	.btnStyle{
		padding: 5px;
		font-size: 16px;
		font-weight: bold;
		color: #09F8BA
	}
	.btnStyle:hover{
		cursor: pointer;
	}
	.search{
		background-color:#F8F8F9;
		padding: 10px;
		margin: 10px;
		border-radius: 10px;
		display: flex;
		flex-direction: row;
	}
</style>
