<template>
  <RouterView />
</template>

<script setup>
import { RouterView } from 'vue-router';
</script>

<style>
:root:root {
  --el-color-primary: #3491FA;
  --el-color-primary-light-3: #9FD4FD;
  --el-color-primary-light-5: #57A9FB;
  --el-color-primary-light-7: #7BC0FC;
  --el-color-primary-light-8: #C3E7FE;
  --el-color-primary-light-9: #E8F7FF;
  --el-color-primary-dark-2: #063078;
}
#app {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

</style>

