import { createI18n } from 'vue-i18n'
import en from './locales/en.json'
import zh from './locales/zh.json'

export const i18n = createI18n({
  legacy: false, // Composition API 模式要設 false
  locale: 'zh',  // 預設語系
  fallbackLocale: 'en',
  messages: {
    zh,
    en
  }
})