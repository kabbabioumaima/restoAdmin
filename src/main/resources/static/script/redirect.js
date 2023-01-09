$('a').removeClass('active');
$('a:contains(Statistiques)').addClass('active');
$("#main-content").load("/admin/index.html");

function show(page) {
	
	if (page == "statistiques") {
		$('a').removeClass('active');
		$('a:contains(Statistiques)').addClass('active');
		$("#main-content").load("/admin/index.html");
		event.preventDefault();
	}
	if (page == "villes") {
		$('a').removeClass('active');
		$('a:contains(Villes)').addClass('active');
		$("#main-content").load("/admin/ville.html");
		event.preventDefault();
	}
	if (page == "series") {
		$('a').removeClass('active');
		$('a:contains(Series)').addClass('active');
		$("#main-content").load("/admin/serie.html");
		event.preventDefault();
	}
	if (page == "restaurants") {
		$('a').removeClass('active');
		$('a:contains(Restaurants)').addClass('active');
		$("#main-content").load("/admin/restaurant.html");
		event.preventDefault();
	}
	
	if (page == "zones") {
		$('a').removeClass('active');
		$('a:contains(Zones)').addClass('active');
		$("#main-content").load("/admin/zone.html");
		event.preventDefault();
	}
	if (page == "specialites") {
		$('a').removeClass('active');
		$('a:contains(Specialites)').addClass('active');
		$("#main-content").load("/admin/Specialite.html");
		event.preventDefault();
	}
}
