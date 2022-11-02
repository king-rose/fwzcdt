<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm"  :inline="true">
      <el-form-item label="模糊查询" prop="gjzcx">
        <el-input
          v-model="queryParams.gjzcx"
          placeholder="请输入关键字查询"
          clearable
          size="small"
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select
          v-model="queryParams.status"
          placeholder="状态"
          clearable
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="dict in zhuangTaiList"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:role:add']"
        >新增</el-button>
      </el-col>
    </el-row>
    <el-table v-loading="loading" :data="ztrwList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="50" align="center" />
      <el-table-column label="功能编号" align="center" width="150" prop="gnbh" />
      <el-table-column label="功能名称" align="center"  prop="gnmc" />
      <el-table-column label="功能描述" align="center"  prop="gnms" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:config:edit']"
          >查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 添加或修改角色配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="功能编号" prop="gnbh">
          <el-input v-model="form.gnbh" placeholder="请输入功能编号" />
        </el-form-item>
        <el-form-item label="功能名称" prop="gnmc">
          <el-input v-model="form.gnmc" placeholder="请输入功能名称" />
        </el-form-item>
        <el-form-item label="功能描述" prop="gnms">
          <el-input v-model="form.gnms" placeholder="请输入功能描述" />
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
  export default {
    name: "application-function",
    data(){
      return{
        ztrwList:[],
        // 遮罩层
        loading: true,
        open:false,
        zhuangTaiList:[
          {value:0,label:'可用'},
          {value:1,label:'不可用'}
        ],
        title:'',
        // 表单参数
        form: {},
        rules:{},
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          gjzcx: undefined,
          status:0
        },
      }
    },
    created() {
      this.getZtrwList()
    },
    methods:{
      /** 重置按钮操作 */
      resetQuery() {
        this.handleQuery();
      },
      /** 新增应用功能 */
      handleAdd(){
        this.open = true;
        this.title = "新增应用功能";
      },
      getZtrwList(){
        this.loading=false
      },
      // 取消按钮
      cancel() {
        this.open = false;
      },
      submitForm(){
        this.open = false
      },
      // 多选框选中数据
      handleSelectionChange(selection) {

      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
      },
    },
  }
</script>

<style scoped>
  .el-header{
    background-color: #f7f7f7;
    border: solid 1px #e2e2e2;
    font-size: 20px;
    border-bottom:none;
    line-height: 60px;
  }
  .el-main {
    font-size: 15px;
    border: solid 1px #e2e2e2;
  }
</style>
