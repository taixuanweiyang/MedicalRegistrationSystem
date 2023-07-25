<template>
    <el-col class="context"><strong>处方记录</strong></el-col>
    <el-divider />
    <el-table :data="tableData" style="width: 100%">
      <el-table-column align="center" prop="patientNumber" label="门诊号"  width="90"/>
      <el-table-column align="center" prop="time" label="开单时间" width="200" />
      <el-table-column align="center" prop="content" label="处方内容" width="230" />
      <el-table-column align="center" prop="feeStatus" label="支付状态" width="200" :formatter="formatFeeStatus"/>
      <el-table-column align="center" prop="doctorId" label="医生Id" width="230" />
      <el-table-column align="center" prop="opetration" label="操作">
        <template #default="{ row }">
          <template v-if="row.feeStatus === false">
            <el-button type="primary" text @click="payPrescription(row)">支付</el-button>
          </template>
          <template v-else>无</template>
        </template>
      </el-table-column>
    </el-table>
</template>

<script>
import axios from "axios";
import { ElMessage } from "element-plus";

export default {
  data() {
    return {
      tableData: [],
    };
  },
  created() {
    this.fetchPrescriptionInfo();
  },
  methods: {
    fetchPrescriptionInfo() {
      axios
        .get("http://8.130.117.156:8080/his/patient/prescription/info", {
          params: {
            patientId: this.$store.state.pInfo.id,
          },
        })
        .then((res) => {
          this.tableData = res.data;
        })
        .catch((error) => {
          ElMessage.error("获取患者处方信息失败：" + error.message);
        });
    },
    formatFeeStatus(row){
      return row.feeStatus ? "已支付" : "未支付";
    },
    payPrescription(row) {
    axios
      .get("http://8.130.117.156:8080/his/patient/prescription/pay", {
        params: {
          patientNumber: row.patientNumber,
        },
      })
      .then((res) => {
        if (res.data) {
        row.feeStatus = 1; 
        ElMessage.success("处方支付成功");
        }
        else{
          ElMessage.error("处方支付失败");
        }
      })
      .catch((error) => {
        console.error("处方支付失败: ", error);
      });
  },
  },
};
</script>