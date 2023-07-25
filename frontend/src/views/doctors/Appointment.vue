<template>
    <el-input v-model="search" placeholder="请输入患者Id" />
    <el-button type="primary" @click="Search()">查询</el-button>
    <el-table :data="searchTableData" style="width: 100%">
      <el-table-column align="center" prop="patientName" label="患者名" width="100" />
      <el-table-column align="center" prop="patientId" label="患者Id" width="200" />
      <el-table-column align="center" prop="doctorName" label="医生名" width="100" />
      <el-table-column align="center" prop="doctorId" label="医生Id" width="200" />
      <el-table-column align="center" prop="patientNumber" label="门诊号"  width="90"/>
      <el-table-column align="center" prop="registTime" label="挂号时间" width="190" />
      <el-table-column align="center" prop="reserveTime" label="预约时间" width="170" />
      <el-table-column align="center" prop="timeRange" label="上午/下午" width="90" :formatter="formatTimeRange"/>
      <el-table-column align="center" prop="registDept" label="挂号科室" width="130" />
      <el-table-column align="center" prop="medicalStatus" label="挂号状态" width="130" :formatter="formatMedicalStatus"/>
    </el-table>
</template>

<style scoped>
.el-input {
    width: 300px;
    margin-right:20px;
  }
</style>

<script>
// 导入axios
import axios from "axios";

export default {
  data() {
    return {
      search: "",
      tableData: [], 
      searchTableData:[],
    };
  },
  created() {
    axios
      .get("http://8.130.117.156:8080/his/doctor/registration/allinfo")
      .then((response) => {
        this.tableData = response.data;
        this.searchTableData = this.tableData;
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  },
  methods: {
    formatTimeRange(row) {
      return row.timeRange ? "下午" : "上午";
    },
    formatMedicalStatus(row) {
      if (row.medicalStatus === -1) {
        return "失效";
      } else if (row.medicalStatus === 0) {
        return "未就诊";
      } else {
        return "已就诊";
      }
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
    }
  },
};
</script>