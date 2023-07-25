<template>
    <el-input v-model="search" placeholder="请输入患者Id" />
    <el-button type="primary" @click="Search()">查询</el-button>
    <el-table :data="searchTableData" style="width: 100%">
      <el-table-column align="center" prop="patientNumber" label="门诊号"  width="90"/>
      <el-table-column align="center" prop="time" label="开单时间" width="230" />
      <el-table-column align="center" prop="content" label="处方内容" width="230" />
      <el-table-column align="center" prop="feeStatus" label="支付状态" width="230" :formatter="formatFeeStatus"/>
      <el-table-column align="center" prop="doctorId" label="医生Id" width="230" />
      <el-table-column align="center" prop="patientId" label="患者Id" width="230" />
    </el-table>
</template>

<style scoped>
.el-input {
    width: 300px;
    margin-right:20px;
  }
</style>

<script>
import axios from "axios";
// import func from 'vue-editor-bridge';
export default {
  data() {
    return {
      search: '',
      tableData: [],
      searchTableData:[],
    }
  },
  created(){
      // 发送GET请求获取挂号信息
      axios.get('http://8.130.117.156:8080/his/doctor/prescription/allinfo')
        .then(response => {
          this.tableData = response.data;
          this.searchTableData = this.tableData;
          console.log(this.searchTableData);
        })
        .catch(error => {
          // 请求失败处理
          console.error('Error fetching data:', error);
        });
  },
  methods:{
    formatFeeStatus(row) {
      return row.feeStatus ? "已缴费" : "未缴费";
    },
    Search() {
      if(this.search === '') {
        this.searchTableData = this.tableData;
      }
      else {
        this.searchTableData = this.tableData.filter((elem) => {
          return elem.patientId == this.search;
        });
      }
      console.log(this.searchTableData);
    }
  }
}
</script>