$(function(){
	// solving the active menu problem
	switch(menu)
	{
	case 'About Us':
		$('#about').addClass('active'); // this id can be followed from the page controller
		break;
	case 'Contact Us':
		$('#contact').addClass('active'); 
		break;
	default: 
		$('#home').addClass('active');
		break;
	}
});








