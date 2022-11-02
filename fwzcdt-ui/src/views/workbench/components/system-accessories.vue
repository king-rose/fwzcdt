<template>
  <div class="app-container">
        <el-form :model="queryParams" ref="queryForm"  :inline="true">
          <el-form-item label="文件名称" prop="wjmc">
            <el-input v-model="queryParams.wjmc" placeholder="请输入文件名称"/>
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
                v-hasPermi="['system:post:add']"
              >新增</el-button>
          </el-col>
        </el-row>
        <el-table v-loading="loading" :data="interfaceList" @selection-change="handleSelectionChange">
          <el-table-column label="文件名称" align="center"  prop="wjmc" />
          <el-table-column label="文件大小" align="center"  prop="wjdx" />
        </el-table>
        <!-- 新增对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="200px">
        <el-form-item label="文件上传">
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
  export default {
    name: "application-function",
    data(){
      return{
        interfaceList:[],
        // 遮罩层
        loading: true,
        open:false,
        title:'',
        // 表单参数
        form: {},
        rules:{},
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          wjc: undefined,
        },
      }
    },
    created() {
      this.getInterfaceList()
    },
    methods:{
      /** 重置按钮操作 */
      resetQuery() {
        this.handleQuery();
      },
      /** 新增应用功能 */
      handleAdd(){
        this.open = true;
        this.title = "新增系统附件";
      },
      getInterfaceList(){
        this.loading=false
        this.interfaceList=[
          {
            wjmc:'privateKey-keystore',
            wjdx:'848.00B'
          },
          {
            wjmc:'publicKey-keystore',
            wjdx:'216.00B'
          }
        ]
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
