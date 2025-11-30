<script setup>
import { onMounted, reactive } from "vue";
import { useI18n } from "vue-i18n";
import TestForm from "./TestForm.vue";
import TestTable from "./TestTable.vue";

const { t } = useI18n();
const form = {
  userName: "",
  password: "sdfsdfs",
  password2: "",
  phone: "fdgfd",
  email: "sdfds@hggfh",
  birth: "1999-01-01",
  sex: "male",
};
const formData = reactive({ ...form });
const fieldDefs = {
  title: { key: "info", label: "個人資訊", require: false, type: "title" },
  userName: {
    key: "userName",
    label: t("userName"),
    require: false,
    type: "text",
  },
  password: {
    key: "password",
    extraKey: ["password2"],
    label: t("password"),
    require: true,
    type: "text",
  },
  phone: { key: "phone", label: t("phone"), require: true, type: "tel" },
  email: { key: "email", label: t("email"), require: true, type: "email" },
  title2: { key: "info2", label: "個人資訊", require: false, type: "title" },
  birth: {
    key: "birth",
    label: t("birth"),
    require: true,
    type: "date",
  },
  sex: { key: "sex", label: t("sex"), require: true, type: "text" },
  file: { key: "file", label: t("sex"), require: true, type: "text" },
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
  for (const key in fieldDefs) {
    if (fieldDefs[key].require && !formData[key]) {
      throw new Error(key + " is null");
    }
    if (fieldDefs[key].extraKey && !formData[fieldDefs[key].extraKey[0]]) {
      console.log(fieldDefs[key].extraKey + " is null!!!");
    }
  }
  // clear();
  // test();
};

const clear = () => {
  const initial = {
    userName: "",
    password: "",
    password2: "",
    phone: "",
    email: "",
    birth: "",
    sex: "",
  };
  Object.assign(formData, initial);
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
