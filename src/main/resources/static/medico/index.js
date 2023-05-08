const app = Vue.createApp({
    
    data() {
      return {
        medicos: []
      }
    },
  
    methods: {
      async leerMedicos() {
          const medicos = await fetch("http://localhost:8080/api/medicos");
          this.medicos = await medicos.json();
      }
    },
  
    mounted() {
      this.leerMedicos();
    }
  }
);