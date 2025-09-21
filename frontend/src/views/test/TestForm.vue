<script setup>
/**
 * Vue 3.4+：用具名 v-model 綁整份表單資料
 * 父層可用 v-model:formData="formData"
 */
const formData = defineModel("formData", { type: Object, required: true });

const props = defineProps({
  layout: { type: Array, required: true }, // e.g. [ ['title'], ['userName'], ['phone','email'] ... ]
  fieldDefs: { type: Object, required: true }, // e.g. { userName:{ key:'userName', labelKey:'userName', type:'text' }, ... }
  // 可選：傳入 t 以支援 i18n（不想在子元件 useI18n）
  t: { type: Function, default: (s) => s },
  handleSubmit: Function,
});

const emit = defineEmits(["submit"]);

const onSubmit = () => {
  emit("submit"); // 讓父層呼叫 handelSubmit
};
</script>

<template>
  <form class="w-1/2 border" @submit.prevent="onSubmit">
    <div class="flex" v-for="(row, i) in layout" :key="i">
      <template v-for="k in row" :key="k">
        <!-- 如果是標題 -->
        <div
          v-if="fieldDefs[k].type === 'title'"
          class="text-center border w-full"
        >
          {{ fieldDefs[k].label }}
        </div>
        <template v-else>
          <label class="w-1/5 border"
            >{{ fieldDefs[k].label }} {{ fieldDefs[k].type }}</label
          >
          <div
            :class="row.length === 1 ? 'w-4/5 border' : 'w-3/10 border'"
            class="flex"
          >
            <template v-if="fieldDefs[k].type === 'text'">
              <input class="w-full" type="text" v-model="formData[k]" />
            </template>

            <template v-else-if="fieldDefs[k].type === 'date'">
              <input
                class="w-full border px-2 py-1"
                type="date"
                v-model="formData[k]"
              />
            </template>

            <template v-else-if="fieldDefs[k].type === 'select'">
              <!-- 下拉選單 -->
            </template>
          </div>
        </template>
      </template>
    </div>
    <div>
      <button type="submit" class="w-1/2 m-2 p-2 bg-blue-700">x</button>
    </div>
  </form>
</template>
