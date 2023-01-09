$(document)
		.ready(
				function() {

					table = $('#trestaurant')
							.DataTable({
										ajax : {
											url : "restos/all",
											dataSrc : ''
										},
										
										columns : [
												{
													data : "id"
												},
												{
													data : "nom"
												},
												{
													data : "adresse"
												},
												{
													data : "lat"
												},
												{
													data : "longs"
												},
												{
													data : "rank"
												},
												{
													data : "heure_op"
												},
												{
													data : "heure_cl"
												},
												{
													data : "week"
												},
												{
													data : "serie.nom"
												},
												
												{
													data : "zone.ville.nom"
												},
												{
													data : "zone.nom"
												},
												
												{
													"render" : function() {
														return '<button type="button" class="btn btn-outline-danger supprimer">Supprimer</button>';
													}
												},
												{
													"render" : function() {
														return '<button type="button" class="btn btn-outline-secondary modifier">Modifier</button>';
													}
												} ]

									});
					
					$.ajax({
						url:'/series/all',
						type:'GET',
						success : function(data) {
							var option = '';
							data.forEach(e=>{
								option += '<option value ='+e.id+'>'+e.nom+'</option>';
							});
							
						$('#serie').append(option);
						},
						error : function(jqXHR, textStatus,
								errorThrown) {
							console.log(textStatus);
						}
						
					});
					$.ajax({
						url:'/zones/all',
						type:'GET',
						success : function(data) {
							var option = '';
							data.forEach(e=>{
								option += '<option value ='+e.id+'>'+e.ville.nom+'</option>';
							});
							
						$('#ville').append(option);
						},
						error : function(jqXHR, textStatus,
								errorThrown) {
							console.log(textStatus);
						}
						
					});
					
					$.ajax({
						url:'/zones/all',
						type:'GET',
						success : function(data) {
							var option = '';
							data.forEach(e=>{
								
								option += '<option value ='+e.id+'>'+e.nom+'</option>';
							});
							
						$('#zone').append(option);
						},
						error : function(jqXHR, textStatus,
								errorThrown) {
							console.log(textStatus);
						}
						
					});

					$('#btn').click(
						 
							function() {
								var nom = $("#nom");
								var adresse = $("#adresse");
								var lat = $("#lat");
								var longs = $("#longs");
								var rank = $("#rank");
								var heure_op = $("#heure_op");
								var heure_cl = $("#heure_cl");
								var week = $("#week");
								var serie = $("#serie");
								var zone = $("#zone");
								if ($('#btn').text() == 'Ajouter') {
									
									var p = {
										nom : nom.val(),
										adresse : adresse.val(),
										lat : lat.val(),
										longs : longs.val(),
										rank : rank.val(),
										heure_op : heure_op.val(),
										heure_cl : heure_cl.val(),
										week : week.val(),
										serie : {
											id : serie.val()
										},
										zone : {
											id : zone.val()
										}
									};

									$.ajax({
										url : 'restos/save',
										contentType : "application/json",
										dataType : "json",
										data : JSON.stringify(p),
										type : 'POST',
										async : false,
										success : function(data, textStatus,
												jqXHR) {
											table.ajax.reload();
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											console.log(textStatus);
										}
									});
									$("#main-content").load(
											"./page/restaurant.html");
								}
							});

					$('#table-content')
							.on(
									'click',
									'.supprimer',
									function() {

										var id = $(this).closest('tr').find(
												'td').eq(0).text();
										var oldLing = $(this).closest('tr')
												.clone();
										var newLigne = '<tr style="position: relative;" class="bg-light" ><th scope="row">'
												+ id
												+ '</th><td colspan="4" style="height: 100%;">';
										newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment supprimer cette marque ? </h4>';
										newLigne += '<button type="button" class="btn btn-outline-primary btn-sm confirmer" style="margin-left: 25px;">Oui</button>';
										newLigne += '<button type="button" class="btn btn-outline-danger btn-sm annuler" style="margin-left: 25px;">Non</button></td></tr>';

										$(this).closest('tr').replaceWith(
												newLigne);
										$('.annuler').click(
												function() {
													$(this).closest('tr')
															.replaceWith(
																	oldLing);
												});
										$('.confirmer')
												.click(
														function(e) {
															e.preventDefault();
															$
																	.ajax({
																		url : 'restos/delete/'
																				+ id,
																		data : {},
																		type : 'DELETE',
																		async : false,
																		success : function(
																				data,
																				textStatus,
																				jqXHR) {
																			if (data
																					.includes("error") == true) {
																				$(
																						"#error")
																						.modal();
																			} else {
																				table.ajax
																						.reload();
																			}
																		},
																		error : function(
																				jqXHR,
																				textStatus,
																				errorThrown) {
																			$(
																					"#error")
																					.modal();
																		}
																	});

														});

									});

					$('#table-content').on(
							'click',
							'.modifier',
							function() {
								
        
								var btn = $('#btn');
								var id = $(this).closest('tr').find('td').eq(0)
										.text();
								
								var nom = $(this).closest('tr').find('td').eq(
										1).text();
										var adresse = $(this).closest('tr').find('td').eq(
										2).text();
										var lat = $(this).closest('tr').find('td').eq(
										3).text();
										var longs = $(this).closest('tr').find('td').eq(
										4).text();
										var rank = $(this).closest('tr').find('td').eq(
										5).text();
								var heure_op = $(this).closest('tr').find('td')
										.eq(6).text();
								var heure_cl = $(this).closest('tr').find('td')
										.eq(7).text();
										var week = $(this).closest('tr').find('td')
										.eq(8).text();
										var serie = $(this).closest('tr').find('td')
										.eq(9).text();
								var zone = $(this).closest('tr').find('td')
								.eq(10).text();
							
								btn.text('Modifier');
								$("#nom").val(nom);
								$("#adresse").val(adresse);
								$("#lat").val(lat);
								$("#longs").val(longs);
								$("#rank").val(rank);
								$("#heure_op").val(heure_op);
								$("#heure_cl").val(heure_cl);
								$("#week").val(week);
								var op = $('#serie option').filter(function () { return $(this).html() == serie; }).val();
								$("#serie").val(op);
								var oq = $('#zone option').filter(function () { return $(this).html() == zone; }).val();
								$("#zone").val(oq);
								$("#id").val(id);
								
								btn.click(function(e) {
									e.preventDefault();
									
									var p = {
										id : $("#id").val(),
										nom : $("#nom").val(),
										adresse : $("#adresse").val(),
										lat : $("#lat").val(),
										longs : $("#longs").val(),
										rank : $("#rank").val(),
										heure_op : $("#heure_op").val(),
										heure_cl : $("#heure_cl").val(),
										week : $("#week").val(),
										serie : {
											id : $("#serie").val()
											
										},
										zone : {
											id : $("#zone").val()
											
										}
										
									};
									if ($('#btn').text() == 'Modifier') {
										$.ajax({
											url : 'restos/save',
											contentType : "application/json",
											dataType : "json",
											data : JSON.stringify(p),
											type : 'POST',
											async : false,
											success : function(data,
													textStatus, jqXHR) {
												table.ajax.reload();
												
								
												$("#nom").val('');
												$("#adresse").val('');
												$("#lat").val('');
												$("#longs").val('');
												$("#rank").val('');
												$("#heure_op").val('');
												$("#heure_cl").val('');
												$("#week").val('');
												$("#serie").val('');
												$("#zone").val('');
												btn.text('Ajouter');
											},
											error : function(jqXHR, textStatus,
													errorThrown) {
												console.log(textStatus);
											}
										});
										$("#main-content").load(
												"./page/restaurant.html");
									}
								});
							});

					
			
});