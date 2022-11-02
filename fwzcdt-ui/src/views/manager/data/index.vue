<template>
	<div class="app-container">
		<el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px">
			<el-form-item label="服务场景查询" label-width="100px" prop="postCode" w>
				<el-switch
					v-model="value1"
					active-text="模糊查询"
					inactive-text="精确查询">
				</el-switch>
			</el-form-item>
			<el-form-item label-width="100px">
				<el-input
					v-model="queryParams.postCode"
					clearable
					placeholder="请输入岗位编码"
					size="small"
					@keyup.enter.native="handleQuery"
				/>
			</el-form-item>

			<el-form-item>
				<el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
			</el-form-item>
		</el-form>

		<el-row :gutter="10" class="mb8">
			<el-col :span="1.5">
				<el-button
					v-hasPermi="['system:post:add']"
					icon="el-icon-plus"
					plain
					size="mini"
					type="primary"
					@click="handleAdd"
				>新增
				</el-button>
			</el-col>
			<el-col :span="1.5">
				<el-button
					v-hasPermi="['system:post:edit']"
					icon="el-icon-edit"
					plain
					size="mini"
					type="success"
					@click="daoRu"
				>导入
				</el-button>
			</el-col>
			<right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
		</el-row>

		<el-table v-loading="loading" :data="postList" @selection-change="handleSelectionChange">
			<el-table-column align="center" type="selection" width="55"/>
			<el-table-column align="center" label="字段名称" prop="zdmc"/>
			<el-table-column align="center" label="字段描述" prop="zdms" width="180"/>
			<el-table-column align="center" label="数据类型" prop="sjlx"/>
			<el-table-column align="center" label="长度类型" prop="cdlx"/>
			<el-table-column align="center" label="字段长度(BYTE)" prop="zdcd"/>
			<el-table-column align="center" label="数据格式" prop="sjgs"/>
			s
			<el-table-column align="center" label="业务含义" prop="ywhy" width="180"/>
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
						<el-form-item label="数据分类" prop="sjfl">
							<el-select v-model="form.sjfl" placeholder="请选择">
								<el-option
									v-for="dict in sjflList"
									:key="dict.value"
									:label="dict.label"
									:value="dict.value"
								></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="标准类型" prop="bzlx">
							<el-select v-model="form.bzlx" placeholder="请选择">
								<el-option
									v-for="dict in bzlxList"
									:key="dict.value"
									:label="dict.label"
									:value="dict.value"
								></el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="字段名称" prop="zdmc">
							<el-input v-model="form.zdmc"/>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="字段描述" prop="szdms">
							<el-input v-model="form.zdms"/>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="数据类型" prop="sjlx">
							<el-select v-model="form.shlx" placeholder="请选择">
								<el-option
									v-for="dict in sjlxList"
									:key="dict.value"
									:label="dict.label"
									:value="dict.value"
								></el-option>
							</el-select>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="长度类型" prop="cdlx">
							<el-select v-model="form.cdlx" placeholder="请选择">
								<el-option
									v-for="dict in cdlxList"
									:key="dict.value"
									:label="dict.label"
									:value="dict.value"
								></el-option>
							</el-select>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="字段长度（BYTE）" prop="zdcd">
							<el-input v-model="form.zdcd"/>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="数据格式" prop="sjgs">
							<el-input v-model="form.sjgs"/>
						</el-form-item>
					</el-col>
				</el-row>
				<el-row>
					<el-col :span="12">
						<el-form-item label="数标信息项编号" prop="sbxxxbh">
							<el-input v-model="form.sbxxxbh"/>
						</el-form-item>
					</el-col>
					<el-col :span="12">
						<el-form-item label="业务含义" prop="ywhy">
							<el-input v-model="form.ywhy"/>
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
			<el-form ref="form" :model="form" :rules="rules" label-width="200px">
				<el-form-item label="文件上传按钮">
					<file-upload v-model="form.fie"/>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button type="primary" @click="submitForm">确 定</el-button>
				<el-button @click="cancel">取 消</el-button>
			</div>
		</el-dialog>
	</div>
</template>

<script>
import {listPost, getPost, delPost, addPost, updatePost} from "@/api/system/post";

export default {
	name: "Post",
	dicts: ['sys_normal_disable'],
	data() {
		return {
			sjflList: [],
			bzlxList: [],
			sjlxList: [],
			cdlxList: [],
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
				pageNum: 1,
				pageSize: 10,
				postCode: undefined,
				postName: undefined,
				status: undefined
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
	},
	methods: {
		/** 查询岗位列表 */
		getList() {
			this.loading = true;
			this.sjflList = [
				{value: '报文头', lable: '报文头'},
				{value: '结算类', lable: '结算类'},
				{value: '总账类', lable: '总账类'},
				{value: '贷款类', lable: '贷款类'},
				{value: '利税费汇币', lable: '利税费汇币'},
				{value: '现金凭证', lable: '现金凭证'},
				{value: '银联卡', lable: '银联卡'},
				{value: '金额余额', lable: '金额余额'},
				{value: '结售汇', lable: '结售汇'},
				{value: '中间业务', lable: '中间业务'},
				{value: '报文头', lable: '报文头'},
				{value: '凭证信息', lable: '凭证信息'},
				{value: '统一支付', lable: '统一支付'},
				{value: '重复项', lable: '重复项'},
				{value: '渠道类', lable: '渠道类'},
				{value: '基础服务', lable: '基础服务'},
				{value: '理财资管', lable: '理财资管'},
				{value: '财政类', lable: '财政类'},
				{value: '交易信息', lable: '交易信息'},
				{value: '产品类', lable: '产品类'},
				{value: '协议类', lable: '协议类'},
				{value: '日期时间', lable: '日期时间'},
				{value: '国家地区', lable: '国家地区'},
				{value: '客户类', lable: '客户类'},
				{value: '账户类', lable: '账户类'},
			]
			this.sjlxList = [
				{value: 'String', lable: 'String'},
				{value: 'Num', lable: 'Num'},
				{value: 'String', lable: 'Array'},
				{value: 'Object', lable: 'Object'},
			]
			this.cdlxList = [
				{value: '定长', lable: '定长'},
				{value: '最长', lable: '最长'}
			]
			this.bzlxList = [
				{value: 'DTSD-标准类型', lable: 'DTSD-标准类型'}
			]
			this.postList = [
				{
					zdmc: 'ZY_ACCT+AMT',
					zdms: '自有资金账户余额',
					sjlx: 'String',
					cdlx: '最长',
					zdcd: '17.2',
					sjgs: 'Decimal(17.2)',
					ywhy: '与ZYSUB_BALANCE重复'
				},
				{
					zdmc: 'ACCR_INTADJ',
					zdms: '计提日利息调整金额',
					sjlx: 'String',
					cdlx: '最长',
					zdcd: '17.2',
					sjgs: 'Decimal(17.2)',
					ywhy: '与INT_ADJ_CTD重复,后续不使用'
				},
				{
					zdmc: 'ACCOUNT_PRODUCTID',
					zdms: '核算产品编号',
					sjlx: 'String',
					cdlx: '最长',
					zdcd: '10',
					sjgs: 'varchar(10)',
					ywhy: '1010013-企业贷;201010-个人贷'
				},
			];
			this.total = 3;
			this.loading = false;
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
		submitForm: function () {
			this.$refs["form"].validate(valid => {
				if (valid) {
					if (this.form.postId != undefined) {
						updatePost(this.form).then(response => {
							this.$modal.msgSuccess("修改成功");
							this.open = false;
							this.getList();
						});
					} else {
						addPost(this.form).then(response => {
							this.$modal.msgSuccess("新增成功");
							this.open = false;
							this.getList();
						});
					}
				}
			});
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
