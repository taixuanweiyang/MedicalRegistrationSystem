<template>
  <el-card class="box-card">
    <el-form ref="registerForm" :model="registerForm" :rules="myrules" label-width="100px">
      <el-radio-group v-model="identity" style="margin-left: 40px">
        <el-radio label="patient" size="large">用户</el-radio>
        <el-radio label="doctor" size="large">医生</el-radio>
      </el-radio-group>
      <el-form-item label="账号：" prop="phone">
        <el-input v-model="registerForm.phone" placeholder="请输入" maxlength="11"/>
      </el-form-item>
      <el-form-item label="密码：" prop="password">
        <el-input v-model="registerForm.password" placeholder="请输入"/>
      </el-form-item>
      <el-form-item label="身份证：" prop="id">
        <el-input v-model="registerForm.id" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="名字：" prop="name">
        <el-input v-model="registerForm.name" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="性别：" prop="sex">
        <el-radio-group v-model="registerForm.sex">
          <el-radio label="1" size="large">男</el-radio>
          <el-radio label="0" size="large">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="年龄：" prop="age">
        <el-input v-model="registerForm.age" placeholder="请输入" />
      </el-form-item>
      <el-form-item v-if="identity=='doctor'" label="科室：" prop="dept">
        <el-select v-model="registerForm.dept"  placeholder="请选择科室">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        />
        </el-select>
      </el-form-item>
      <el-form-item v-if="identity=='doctor'" label="挂号费：" prop="fee">
        <el-input v-model="registerForm.fee" placeholder="挂号费" />
      </el-form-item>
      <el-form-item v-if="identity=='doctor'" label="个人介绍：" prop="introduction">
        <el-input v-model="registerForm.introduction" placeholder="个人介绍（可选）" />
      </el-form-item>
      <div id="center">
      <el-button type="primary" :loading="loginLoading" @click="onRegister">完成</el-button>
      <el-button @click="$router.push('/')">取消</el-button>
      </div>
    </el-form>
    <el-dialog
    v-model="dialogVisible"
    title="提示"
    width="30%"
    :before-close="handleClose">
    <span>注册成功</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false,$router.push('/')">
          完成
        </el-button>
      </span>
    </template>
  </el-dialog>
  </el-card>
  
</template>

<style scoped>
.box-card {
  margin-left:38%;
  margin-top:8%;
  width: 450px;
  /* height: 300px; */
  position: absolute;
}
.el-input{
  margin-top:5px;
  margin-bottom:5px;
}
#center{
  margin: auto;
  margin-left: 10%;
}
</style>

<script>
import axios from 'axios'
import{ElMessage}from 'element-plus'
export default {
  data() {
    return {
      registerForm:{
        phone: '',
        password: '',
        id:'',
        name:'',
        sex:'1',
        age:'',
        dept:'',
        fee:'',
        introduction:'',
      },
      identity:'patient',
      options:[
      {
        value: '儿科',
        label: '儿科',
      },
      {
        value: '内科',
        label: '内科',
      },
      {
        value: '外科',
        label: '外科',
      },
      {
        value: '妇产科',
        label: '妇产科',
      },
      {
        value: '泌尿科',
        label: '泌尿科',
      },
      {
        value: '皮肤科',
        label: '皮肤科',
      },
      {
        value: '眼科',
        label: '眼科',
      },
      {
        value: '骨科',
        label: '骨科',
      },
      {
        value: '麻醉科',
        label: '麻醉科',
      }],
      isSuccess:false,
      dialogVisible:false,
      loginLoading: false,
      myrules: {
        phone: [
            {required: true,message: "账号不能为空！",trigger: "change"},
            {pattern: /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/,message: "请输入正确的手机号码！",trigger: "change"}
          ],
        password:[
            { required: true, message: '密码不能为空！', trigger: 'change' },
            { type: 'string', pattern: /^.{6,16}$/, message: '密码仅限6~16个字符！', trigger: 'change' }
          ],
        id:[
            { required: true, message: '身份证不能为空！', trigger: 'change' },
            { type: 'string', pattern: /^([1-6][1-9]|50)\d{4}(18|19|20)\d{2}((0[1-9])|10|11|12)(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/, message: '请输入正确的身份证！', trigger: 'change' }
          ], 
        name:[
            { required: true, message: '姓名不能为空！', trigger: 'change' },
          ], 
        age:[
            { required: true, message: '年龄不能为空！', trigger: 'change' },
          ], 
        dept:[
            { required: true, message: '科室不能为空！', trigger: 'change' },
          ], 
        fee:[
            { required: true, message: '挂号费不能为空！', trigger: 'change' },
          ], 
        }
      }
  },
  methods: {
    onRegister () {
      this.$refs.registerForm.validate(valid => {
        if (!valid) {
          return false
        }
        this.Register()
      })
    },
    Register () {
      let apiUrl = "";
      let postData = {
        phone: this.registerForm.phone,
        password: this.registerForm.password,
        id: this.registerForm.id,
        name: this.registerForm.name,
        sex: this.registerForm.sex === '1',
        age: parseInt(this.registerForm.age),
        fee: 0.0,
        dept: '',
        introduction: '',
      };
      if (this.identity === 'patient') {
        apiUrl = 'http://8.130.117.156:8080/his/patient/signup';
      } else if (this.identity === 'doctor') {
        apiUrl = 'http://8.130.117.156:8080/his/doctor/signup';
        postData.fee = parseFloat(this.registerForm.fee);
        postData.dept = this.registerForm.dept,
        postData.introduction = this.registerForm.introduction;
      } else {
        ElMessage.error('出错！');
        return;
      }
      console.log(postData);
      this.loginLoading = true;
      axios.post(apiUrl, postData)
        .then(res => {
          this.isSuccess = res.data.success;
          if (!this.isSuccess) {
            this.dialogVisible = true;
            this.loginLoading = false;
          } else {
            ElMessage.error('账号已存在或存在错误');
          }
        })
        .catch(error => {
          console.error(error);
          ElMessage.error('请求出错，请检查API URL地址');
          this.loginLoading = false;
        });
    }
  }
}
</script>
