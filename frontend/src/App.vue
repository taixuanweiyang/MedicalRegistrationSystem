<template>
  <div id="nav">
    <vue-particles
      color="#555"
      :particleOpacity="2"
      :particlesNumber="150"
      shapeType="circle"
      :particleSize="4"
      linesColor="#555"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.4"
      :linesDistance="150"
      :moveSpeed="2"
      :hoverEffect="true"
      hoverMode="grab"
      :clickEffect="false"
      clickMode="push"
      class="lizi"
    />
  </div>
  <RouterView />
</template>

<script setup>
import { RouterView } from 'vue-router';
</script>

<style >
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
.lizi{
  position:absolute;
  top:0;
  width:100%;
}

</style>

