$(function() {
	// solving the active menu problem
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active'); // this id can be followed from the page
		// controller
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}
	// to tackle the csrf token
	var token = $('meta[name="_csrf"]').attr('content'); // it will call the
	// content from the
	// meta page.jsp
	var token = $('meta[name="_csrf_header"]').attr('content');

	if (token.length > 0 && header.length > 0) {
		// set the token header for the ajax request
		$(document).ajaxsend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);

		});

	}
	// code for jquery dataTable

	/*
	 * create a dataSet var products = [ [ '1', 'ABC' ], [ '2', 'CDE' ], [ '3',
	 * 'EFG' ], [ '4', 'GHI' ], [ '5', 'IJK' ], [ '6', 'KLM' ], [ '7', 'MNO' ], [
	 * '8', 'OPQ' ] ];
	 */

	var $table = $('#productListTable'); // # is the id selector

	// execute the below code only where we have this table
	if ($table.length) {
		// console.log('Inside the table!');

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products'; // window.categoryId
			// this is we've added
			// in the listProduct in
			// the script tag so it
			// can call it from
			// there

		}

		$table
				.DataTable({

					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''

					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImage"/>';
								}
							},

							{
								data : 'name'

							},
							{
								data : 'brand'

							},
							{
								data : 'unitprice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'quanitity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out Of Stock!</span>';

									}
									return data;
								}
							},
							{
								data : 'id',
								bSortable : false,

								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class=btn btn-primary""><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									if (userRole == 'ADMIN') {
										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';

									} else {

										if (row.quanitity < 1) {
											str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';

										} else {

											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										}

									}

									return str;
								}
							}

					]

				});
	}

	// dismissing the alert after 3 seconds
	var $alert = $('.alert');

	if ($alert.length) {
		setTimeout(function() {

			$alert.fadeOut('slow');
		}, 3000)
	}

	// -----------------------------

	// -------------------------
	// data table for admin
	// --------------------------

	var $adminProductstable = $('#adminProductsTable'); // # is the id selector

	// execute the below code only where we have this table
	if ($adminProductstable.length) {
		// console.log('Inside the table!');

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminProductstable
				.DataTable({

					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '20 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''

					},
					columns : [

							{
								data : 'id'
							},

							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="adminDataTableImg"/>';
								}
							},

							{
								data : 'name'

							},
							{
								data : 'brand'

							},
							{
								data : 'quanitity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out Of Stock!</span>';

									}
									return data;
								}
							},
							{
								data : 'unitprice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},

							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';

									str += '<label class="switch">';
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '" />';
									} else {
										str += '<input type="checkbox" value="'
												+ row.id + '" />';
									}

									str += '<div class="slider" ></div></label>';

									return str;
								}

							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';

									str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-warning">';
									str += '<span class="glyphicon glyphicon-pencil"></span></a>';

									return str;

								}
							}

					],
					initComplete : function() {
						var api = this.api();
						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() // chenge is an event and
										// function is the handler
										{
											var checkbox = $(this); // this is
											// jquery
											// wrapper
											// which is
											// surounded
											var checked = checkbox
													.prop('checked');
											var dMsg = (checked) ? 'You Want to Activate the Product?'
													: 'You Want to Deactivate the Product';
											var value = checkbox.prop('value');

											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation & Deactivation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {
																console
																		.log(value);

																var activationUrl = window.contextRoot
																		+ '/manage/product/'
																		+ value
																		+ '/activation';
																$
																		.post(
																				activationUrl,
																				function(
																						data) {
																					bootbox
																							.alert({
																								size : 'medium',
																								title : 'Information',
																								message : data

																							});
																				});

															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}

													});

										});
					}

				});
	}

	// ----------------------
	// validation code for category

	var $categoryForm = $('#categoryForm');

	if ($categoryForm.length) {
		$categoryForm
				.validate({
					rules : {
						name : {
							required : true,
							minlength : 2
						},
						description : {
							required : true
						}
					},
					messages : {
						name : {
							required : 'Please add the category name!',
							minlength : 'The category name should not be less than 2 characters'
						},
						description : {
							required : 'Please add a description for this category!'
						}
					},
					errorElement : 'em',
					errorPlacement : function(error, element) {
						// add the class of help-block
						error.addClass('help-block');
						// add the error element after the input element
						error.insertAfter(element);

					}

				});
	}
	// -----------
	// validation code for category

	var $loginForm = $('#loginForm');

	if ($loginForm.length) {
		$loginForm.validate({
			rules : {
				username : {
					required : true,
					email : true
				},
				password : {
					required : true
				}
			},
			messages : {
				username : {
					required : 'Please enter the username!',
					email : 'Please Enter Valid Email Address'
				},
				password : {
					required : 'Please Enter the Password!'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				// add the class of help-block
				error.addClass('help-block');
				// add the error element after the input element
				error.insertAfter(element);

			}

		});
	}

	// ------------------------
	// handling the click event of refresh cart button
	$('button[name="refreshCart"]').click(function() {
		// fetch the cart line id
		var cartLineId = $(this).attr('value');
		var countElement = $('#count_' + cartLineId);

		var originalCount = countElement.attr('value');
		var currentCount = countElement.val();

		// work only when the count has changed
		if (currentCount !== originalCount) {
			// current count and original count we can see that in the view page
			// source
			if(currentCount < 1 || currentCount > 3)
				{
					// reverting back to the original count
					// user has given value below 1 and above 3
				
					countElement.val(originalCount);
					bootbox.alert({
						size: 'medium',
						title: 'Error',
						message: 'Product count should be minimum 1 and maximum 3!'
					});	
				}
		}
		else
			{
				var updateUrl = window.contextRoot + '/cart' + cartLineId + '/update?count=' + currentCount;
				//forward it to the controller
				window.location.href = updateUrl;
				
				
			}
	});
	// ------------------------
});