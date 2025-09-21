<script setup lang="js">
</script>

<template>
  <div class="min-h-screen w-full bg-gray-900 text-white">
    <!-- 導覽列 -->
    <nav class="w-full p-4 flex gap-4 bg-white text-black">
      <router-link to="/">首頁</router-link>
      <router-link to="/test">註冊</router-link>
    </nav>

    <!-- 主要內容區 -->
    <main class="w-full p-4">
      <router-view />
    </main>
  </div>
</template>
    <!-- 這個一定要有，路由頁才會被渲染
    <router-view />
  </div> -->
  <!-- <form class="text-center">
    <h1 class="text-3xl font-bold mb-4">歡迎註冊</h1>
    <table border="1" cellpadding="8" cellspacing="0" class="w-full">
      <tbody>
      </tbody>
    </table>
    <button class="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600">
      點我
    </button>
  </form> -->

