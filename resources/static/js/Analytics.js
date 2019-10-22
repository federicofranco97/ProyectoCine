        var listaContenido;

        function LoadGraphics(){
            var ctx = document.getElementById('myChart').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio','Julio'
                            ,'Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                            datasets: [{
                                label: 'Entradas vendidas de forma presencial',
                               data: [listaContenido[0].onsiteSales, listaContenido[1].onsiteSales, listaContenido[2].onsiteSales,
                                    listaContenido[3].onsiteSales, listaContenido[4].onsiteSales, listaContenido[5].onsiteSales,
                                    listaContenido[6].onsiteSales, listaContenido[7].onsiteSales, listaContenido[8].onsiteSales,
                                    listaContenido[9].onsiteSales, listaContenido[10].onsiteSales, listaContenido[11].onsiteSales],
                                backgroundColor: [
                                    'rgba(255, 0, 0, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(0, 0, 0, 1)'
                                ],
                                borderWidth: 2
                            }]
                        },
                        options: {
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                            }
                        }
                    });

                    var ctx = document.getElementById('myChart2').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio','Julio'
                            ,'Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                            datasets: [{
                                label: 'Entradas vendidas de forma online',
                                 data: [listaContenido[0].onlineSales, listaContenido[1].onlineSales, listaContenido[2].onlineSales,
                                      listaContenido[3].onlineSales, listaContenido[4].onlineSales, listaContenido[5].onlineSales,
                                      listaContenido[6].onlineSales, listaContenido[7].onlineSales, listaContenido[8].onlineSales,
                                      listaContenido[9].onlineSales, listaContenido[10].onlineSales, listaContenido[11].onlineSales],
                                backgroundColor: [
                                    'rgba(255, 0, 0, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(0, 0, 0, 1)'
                                ],
                                borderWidth: 2
                            }]
                        },
                        options: {
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                            }
                        }
                    });

                    var ctx = document.getElementById('myChart3').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'doughnut',
                        data: {
                            labels: ['Empelados Activos', 'Supervisores Activos', 'Administradores Activos'],
                            datasets: [{
                                data: [21, 9, 3],
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)',
                                    'rgba(255, 206, 86, 0.2)',
                                ],
                                borderColor: [
                                    'rgba(0, 0, 0, 1)',
                                    'rgba(0, 0, 0, 1)',
                                    'rgba(0, 0, 0, 1)',
                                ],
                                borderWidth: 1
                            }]
                        }
                    });
        }
