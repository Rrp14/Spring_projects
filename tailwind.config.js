/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}", "./public/**/*.html"],
  theme: {
    extend: {
      colors: {
        white: "#fefffe",
        "mint-green": "#e5fcf5",
        celadon: "#b3dec1",
        "dark-purple": "#210124",
        claret: "#750d37",
      },
    },
  },
  plugins: [],
};
