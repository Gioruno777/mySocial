<script setup>
import { onMounted, reactive } from "vue";
import { useI18n } from "vue-i18n";
import TestForm from "./TestForm.vue";
import TestTable from "./TestTable.vue";

const { t } = useI18n();
const form = {
  userName: "",
  password: "",
  phone: "",
  email: "",
  birth: "",
};
const formData = reactive({ ...form });
const fieldDefs = {
  title: { key: "info", label: "個人資訊", type: "title" },
  userName: { key: "userName", label: t("userName"), type: "text" },
  password: { key: "password", label: t("password"), type: "text" },
  phone: { key: "phone", label: t("phone"), type: "tel" },
  email: { key: "email", label: t("email"), type: "email" },
  title2: { key: "info2", label: "個人資訊", type: "title" },
  birth: { key: "birth", label: t("birth"), type: "date" },
  sex: { key: "sex", label: t("sex"), type: "text" },
};
const layout = [
  ["title"],
  ["userName"],
  ["password"],
  ["phone", "email"],
  ["title2"],
  ["birth"],
  ["sex"],
];

onMounted(() => {
  test();
});
const handelSubmit = () => {
  console.log("submit:", JSON.parse(JSON.stringify(formData)));
  test();
};

const test = () => {
  console.log("haha");
};
</script>
<template>
  <h2>This is TestView!!</h2>
  <TestForm
    v-model:formData="formData"
    :layout="layout"
    :fieldDefs="fieldDefs"
    :t="t"
    @submit="handelSubmit"
  />
  <TestTable />
</template>
