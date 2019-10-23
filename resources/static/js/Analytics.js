        var ContentList;
        var ContentMovements;

        function LoadGraphics(){
            var ctx = document.getElementById('myChart').getContext('2d');
                    var myChart = new Chart(ctx, {
                        type: 'line',
                        data: {
                            labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio','Julio'
                            ,'Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                            datasets: [{
                                label: 'Importe de ventas de manera presencial',
                               data: [ContentList[0].onsiteSales, ContentList[1].onsiteSales, ContentList[2].onsiteSales,
                                    ContentList[3].onsiteSales, ContentList[4].onsiteSales, ContentList[5].onsiteSales,
                                    ContentList[6].onsiteSales, ContentList[7].onsiteSales, ContentList[8].onsiteSales,
                                    ContentList[9].onsiteSales, ContentList[10].onsiteSales, ContentList[11].onsiteSales],
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
                                label: 'Importe de ventas de manera online',
                                 data: [ContentList[0].onlineSales, ContentList[1].onlineSales, ContentList[2].onlineSales,
                                      ContentList[3].onlineSales, ContentList[4].onlineSales, ContentList[5].onlineSales,
                                      ContentList[6].onlineSales, ContentList[7].onlineSales, ContentList[8].onlineSales,
                                      ContentList[9].onlineSales, ContentList[10].onlineSales, ContentList[11].onlineSales],
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
                    if(ContentMovements[0]===0,ContentMovements[1]===0,ContentMovements[2]===0){
                        document.getElementById("myChart3").style.display="none";
                        document.getElementById("activityTitle").style.display="none";
                    }
                    var myChart = new Chart(ctx, {
                        type: 'doughnut',
                        data: {
                            labels: ['Administradores Activos', 'Supervisores Activos', 'Empleados Activos'],
                            datasets: [{
                                data: [ContentMovements[0], ContentMovements[1], ContentMovements[2]],
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
