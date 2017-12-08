<style>
.pagination {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	padding-left: 0;
	list-style: none;
	border-radius: .25rem
}

.page-item:first-child .page-link {
	margin-left: 0;
	border-bottom-left-radius: .25rem;
	border-top-left-radius: .25rem
}

.page-item:last-child .page-link {
	border-bottom-right-radius: .25rem;
	border-top-right-radius: .25rem
}

.page-item.active .page-link {
	z-index: 2;
	color: #fff;
	background-color: #0275d8;
	border-color: #0275d8
}

.page-item.disabled .page-link {
	color: #636c72;
	pointer-events: none;
	cursor: not-allowed;
	background-color: #fff;
	border-color: #ddd
}

.page-link {
	position: relative;
	display: block;
	padding: .5rem .75rem;
	margin-left: -1px;
	line-height: 1.25;
	color: #0275d8;
	background-color: #fff;
	border: 1px solid #ddd
}

.page-link:focus, .page-link:hover {
	color: #014c8c;
	text-decoration: none;
	background-color: #eceeef;
	border-color: #ddd
}

.pagination-lg .page-link {
	padding: .75rem 1.5rem;
	font-size: 1.25rem
}

.pagination-lg .page-item:first-child .page-link {
	border-bottom-left-radius: .3rem;
	border-top-left-radius: .3rem
}

.pagination-lg .page-item:last-child .page-link {
	border-bottom-right-radius: .3rem;
	border-top-right-radius: .3rem
}

.pagination-sm .page-link {
	padding: .25rem .5rem;
	font-size: .875rem
}

.pagination-sm .page-item:first-child .page-link {
	border-bottom-left-radius: .2rem;
	border-top-left-radius: .2rem
}

.pagination-sm .page-item:last-child .page-link {
	border-bottom-right-radius: .2rem;
	border-top-right-radius: .2rem
}

.badge {
	display: inline-block;
	padding: .25em .4em;
	font-size: 75%;
	font-weight: 700;
	line-height: 1;
	color: #fff;
	text-align: center;
	white-space: nowrap;
	vertical-align: baseline;
	border-radius: .25rem
}

.badge:empty {
	display: none
}

.btn .badge {
	position: relative;
	top: -1px
}

a.badge:focus, a.badge:hover {
	color: #fff;
	text-decoration: none;
	cursor: pointer
}

.badge-pill {
	padding-right: .6em;
	padding-left: .6em;
	border-radius: 10rem
}

.badge-default {
	background-color: #636c72
}

.badge-default[href]:focus, .badge-default[href]:hover {
	background-color: #4b5257
}

.badge-primary {
	background-color: #0275d8
}

.badge-primary[href]:focus, .badge-primary[href]:hover {
	background-color: #025aa5
}

.badge-success {
	background-color: #5cb85c
}

.badge-success[href]:focus, .badge-success[href]:hover {
	background-color: #449d44
}

.badge-info {
	background-color: #5bc0de
}

.badge-info[href]:focus, .badge-info[href]:hover {
	background-color: #31b0d5
}

.badge-warning {
	background-color: #f0ad4e
}

.badge-warning[href]:focus, .badge-warning[href]:hover {
	background-color: #ec971f
}

.badge-danger {
	background-color: #d9534f
}

.badge-danger[href]:focus, .badge-danger[href]:hover {
	background-color: #c9302c
}

.jumbotron {
	padding: 2rem 1rem;
	margin-bottom: 2rem;
	background-color: #eceeef;
	border-radius: .3rem
}

@media ( min-width :576px) {
	.jumbotron {
		padding: 4rem 2rem
	}
}

.jumbotron-hr {
	border-top-color: #d0d5d8
}

.jumbotron-fluid {
	padding-right: 0;
	padding-left: 0;
	border-radius: 0
}

.alert {
	padding: .75rem 1.25rem;
	margin-bottom: 1rem;
	border: 1px solid transparent;
	border-radius: .25rem
}

.alert-heading {
	color: inherit
}

.alert-link {
	font-weight: 700
}

.alert-dismissible .close {
	position: relative;
	top: -.75rem;
	right: -1.25rem;
	padding: .75rem 1.25rem;
	color: inherit
}

.alert-success {
	background-color: #dff0d8;
	border-color: #d0e9c6;
	color: #3c763d
}

.alert-success hr {
	border-top-color: #c1e2b3
}

.alert-success .alert-link {
	color: #2b542c
}

.alert-info {
	background-color: #d9edf7;
	border-color: #bcdff1;
	color: #31708f
}

.alert-info hr {
	border-top-color: #a6d5ec
}

.alert-info .alert-link {
	color: #245269
}

.alert-warning {
	background-color: #fcf8e3;
	border-color: #faf2cc;
	color: #8a6d3b
}

.alert-warning hr {
	border-top-color: #f7ecb5
}

.alert-warning .alert-link {
	color: #66512c
}

.alert-danger {
	background-color: #f2dede;
	border-color: #ebcccc;
	color: #a94442
}

.alert-danger hr {
	border-top-color: #e4b9b9
}

.alert-danger .alert-link {
	color: #843534
}

@
-webkit-keyframes progress-bar-stripes {
	from {background-position: 1rem 0
}

to {
	background-position: 0 0
}

}
@
-o-keyframes progress-bar-stripes {
	from {background-position: 1rem 0
}

to {
	background-position: 0 0
}

}
@
keyframes progress-bar-stripes {
	from {background-position: 1rem 0
}

to {
	background-position: 0 0
}

}
.progress {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	overflow: hidden;
	font-size: .75rem;
	line-height: 1rem;
	text-align: center;
	background-color: #eceeef;
	border-radius: .25rem
}

.progress-bar {
	height: 1rem;
	color: #fff;
	background-color: #0275d8
}

.progress-bar-striped {
	background-image: -webkit-linear-gradient(45deg, rgba(255, 255, 255, .15)
		25%, transparent 25%, transparent 50%, rgba(255, 255, 255, .15) 50%,
		rgba(255, 255, 255, .15) 75%, transparent 75%, transparent);
	background-image: -o-linear-gradient(45deg, rgba(255, 255, 255, .15) 25%,
		transparent 25%, transparent 50%, rgba(255, 255, 255, .15) 50%,
		rgba(255, 255, 255, .15) 75%, transparent 75%, transparent);
	background-image: linear-gradient(45deg, rgba(255, 255, 255, .15) 25%,
		transparent 25%, transparent 50%, rgba(255, 255, 255, .15) 50%,
		rgba(255, 255, 255, .15) 75%, transparent 75%, transparent);
	-webkit-background-size: 1rem 1rem;
	background-size: 1rem 1rem
}

.progress-bar-animated {
	-webkit-animation: progress-bar-stripes 1s linear infinite;
	-o-animation: progress-bar-stripes 1s linear infinite;
	animation: progress-bar-stripes 1s linear infinite
}

.media {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-align: start;
	-webkit-align-items: flex-start;
	-ms-flex-align: start;
	align-items: flex-start
}

.media-body {
	-webkit-box-flex: 1;
	-webkit-flex: 1 1 0%;
	-ms-flex: 1 1 0%;
	flex: 1 1 0%
}

.list-group {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	-webkit-flex-direction: column;
	-ms-flex-direction: column;
	flex-direction: column;
	padding-left: 0;
	margin-bottom: 0
}

.list-group-item-action {
	width: 100%;
	color: #464a4c;
	text-align: inherit
}

.list-group-item-action .list-group-item-heading {
	color: #292b2c
}

.list-group-item-action:focus, .list-group-item-action:hover {
	color: #464a4c;
	text-decoration: none;
	background-color: #f7f7f9
}

.list-group-item-action:active {
	color: #292b2c;
	background-color: #eceeef
}

.list-group-item {
	position: relative;
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-flex-flow: row wrap;
	-ms-flex-flow: row wrap;
	flex-flow: row wrap;
	-webkit-box-align: center;
	-webkit-align-items: center;
	-ms-flex-align: center;
	align-items: center;
	padding: .75rem 1.25rem;
	margin-bottom: -1px;
	background-color: #fff;
	border: 1px solid rgba(0, 0, 0, .125)
}

.list-group-item:first-child {
	border-top-right-radius: .25rem;
	border-top-left-radius: .25rem
}

.list-group-item:last-child {
	margin-bottom: 0;
	border-bottom-right-radius: .25rem;
	border-bottom-left-radius: .25rem
}

.list-group-item:focus, .list-group-item:hover {
	text-decoration: none
}

.list-group-item.disabled, .list-group-item:disabled {
	color: #636c72;
	cursor: not-allowed;
	background-color: #fff
}

.list-group-item.disabled .list-group-item-heading, .list-group-item:disabled .list-group-item-heading
	{
	color: inherit
}

.list-group-item.disabled .list-group-item-text, .list-group-item:disabled .list-group-item-text
	{
	color: #636c72
}

.list-group-item.active {
	z-index: 2;
	color: #fff;
	background-color: #0275d8;
	border-color: #0275d8
}

.list-group-item.active .list-group-item-heading, .list-group-item.active .list-group-item-heading>.small,
	.list-group-item.active .list-group-item-heading>small {
	color: inherit
}

.list-group-item.active .list-group-item-text {
	color: #daeeff
}

.list-group-flush .list-group-item {
	border-right: 0;
	border-left: 0;
	border-radius: 0
}

.list-group-flush:first-child .list-group-item:first-child {
	border-top: 0
}

.list-group-flush:last-child .list-group-item:last-child {
	border-bottom: 0
}

.list-group-item-success {
	color: #3c763d;
	background-color: #dff0d8
}

a.list-group-item-success, button.list-group-item-success {
	color: #3c763d
}

a.list-group-item-success .list-group-item-heading, button.list-group-item-success .list-group-item-heading
	{
	color: inherit
}

a.list-group-item-success:focus, a.list-group-item-success:hover, button.list-group-item-success:focus,
	button.list-group-item-success:hover {
	color: #3c763d;
	background-color: #d0e9c6
}

a.list-group-item-success.active, button.list-group-item-success.active
	{
	color: #fff;
	background-color: #3c763d;
	border-color: #3c763d
}

.list-group-item-info {
	color: #31708f;
	background-color: #d9edf7
}

a.list-group-item-info, button.list-group-item-info {
	color: #31708f
}

a.list-group-item-info .list-group-item-heading, button.list-group-item-info .list-group-item-heading
	{
	color: inherit
}

a.list-group-item-info:focus, a.list-group-item-info:hover, button.list-group-item-info:focus,
	button.list-group-item-info:hover {
	color: #31708f;
	background-color: #c4e3f3
}

a.list-group-item-info.active, button.list-group-item-info.active {
	color: #fff;
	background-color: #31708f;
	border-color: #31708f
}

.list-group-item-warning {
	color: #8a6d3b;
	background-color: #fcf8e3
}

a.list-group-item-warning, button.list-group-item-warning {
	color: #8a6d3b
}

a.list-group-item-warning .list-group-item-heading, button.list-group-item-warning .list-group-item-heading
	{
	color: inherit
}

a.list-group-item-warning:focus, a.list-group-item-warning:hover, button.list-group-item-warning:focus,
	button.list-group-item-warning:hover {
	color: #8a6d3b;
	background-color: #faf2cc
}

a.list-group-item-warning.active, button.list-group-item-warning.active
	{
	color: #fff;
	background-color: #8a6d3b;
	border-color: #8a6d3b
}

.list-group-item-danger {
	color: #a94442;
	background-color: #f2dede
}

a.list-group-item-danger, button.list-group-item-danger {
	color: #a94442
}

a.list-group-item-danger .list-group-item-heading, button.list-group-item-danger .list-group-item-heading
	{
	color: inherit
}

a.list-group-item-danger:focus, a.list-group-item-danger:hover, button.list-group-item-danger:focus,
	button.list-group-item-danger:hover {
	color: #a94442;
	background-color: #ebcccc
}

a.list-group-item-danger.active, button.list-group-item-danger.active {
	color: #fff;
	background-color: #a94442;
	border-color: #a94442
}

.embed-responsive {
	position: relative;
	display: block;
	width: 100%;
	padding: 0;
	overflow: hidden
}

.embed-responsive::before {
	display: block;
	content: ""
}

.embed-responsive .embed-responsive-item, .embed-responsive embed,
	.embed-responsive iframe, .embed-responsive object, .embed-responsive video
	{
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 100%;
	border: 0
}

.embed-responsive-21by9::before {
	padding-top: 42.857143%
}

.embed-responsive-16by9::before {
	padding-top: 56.25%
}

.embed-responsive-4by3::before {
	padding-top: 75%
}

.embed-responsive-1by1::before {
	padding-top: 100%
}

.close {
	float: right;
	font-size: 1.5rem;
	font-weight: 700;
	line-height: 1;
	color: #000;
	text-shadow: 0 1px 0 #fff;
	opacity: .5
}

.close:focus, .close:hover {
	color: #000;
	text-decoration: none;
	cursor: pointer;
	opacity: .75
}

button.close {
	padding: 0;
	cursor: pointer;
	background: 0 0;
	border: 0;
	-webkit-appearance: none
}

.modal-open {
	overflow: hidden
}

.modal {
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	z-index: 1050;
	display: none;
	overflow: hidden;
	outline: 0
}

.modal.fade .modal-dialog {
	-webkit-transition: -webkit-transform .3s ease-out;
	transition: -webkit-transform .3s ease-out;
	-o-transition: -o-transform .3s ease-out;
	transition: transform .3s ease-out;
	transition: transform .3s ease-out, -webkit-transform .3s ease-out,
		-o-transform .3s ease-out;
	-webkit-transform: translate(0, -25%);
	-o-transform: translate(0, -25%);
	transform: translate(0, -25%)
}

.modal.show .modal-dialog {
	-webkit-transform: translate(0, 0);
	-o-transform: translate(0, 0);
	transform: translate(0, 0)
}

.modal-open .modal {
	overflow-x: hidden;
	overflow-y: auto
}

.modal-dialog {
	position: relative;
	width: auto;
	margin: 10px
}

.modal-content {
	position: relative;
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-orient: vertical;
	-webkit-box-direction: normal;
	-webkit-flex-direction: column;
	-ms-flex-direction: column;
	flex-direction: column;
	background-color: #fff;
	-webkit-background-clip: padding-box;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: .3rem;
	outline: 0
}

.modal-backdrop {
	position: fixed;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	z-index: 1040;
	background-color: #000
}

.modal-backdrop.fade {
	opacity: 0
}

.modal-backdrop.show {
	opacity: .5
}

.modal-header {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-align: center;
	-webkit-align-items: center;
	-ms-flex-align: center;
	align-items: center;
	-webkit-box-pack: justify;
	-webkit-justify-content: space-between;
	-ms-flex-pack: justify;
	justify-content: space-between;
	padding: 15px;
	border-bottom: 1px solid #eceeef
}

.modal-title {
	margin-bottom: 0;
	line-height: 1.5
}

.modal-body {
	position: relative;
	-webkit-box-flex: 1;
	-webkit-flex: 1 1 auto;
	-ms-flex: 1 1 auto;
	flex: 1 1 auto;
	padding: 15px
}

.modal-footer {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-align: center;
	-webkit-align-items: center;
	-ms-flex-align: center;
	align-items: center;
	-webkit-box-pack: end;
	-webkit-justify-content: flex-end;
	-ms-flex-pack: end;
	justify-content: flex-end;
	padding: 15px;
	border-top: 1px solid #eceeef
}

.modal-footer>:not (:first-child ){
	margin-left: .25rem
}

.modal-footer>:not (:last-child ){
	margin-right: .25rem
}

.modal-scrollbar-measure {
	position: absolute;
	top: -9999px;
	width: 50px;
	height: 50px;
	overflow: scroll
}

@media ( min-width :576px) {
	.modal-dialog {
		max-width: 500px;
		margin: 30px auto
	}
	.modal-sm {
		max-width: 300px
	}
}

@media ( min-width :992px) {
	.modal-lg {
		max-width: 800px
	}
}

.tooltip {
	position: absolute;
	z-index: 1070;
	display: block;
	font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI",
		Roboto, "Helvetica Neue", Arial, sans-serif;
	font-style: normal;
	font-weight: 400;
	letter-spacing: normal;
	line-break: auto;
	line-height: 1.5;
	text-align: left;
	text-align: start;
	text-decoration: none;
	text-shadow: none;
	text-transform: none;
	white-space: normal;
	word-break: normal;
	word-spacing: normal;
	font-size: .875rem;
	word-wrap: break-word;
	opacity: 0
}

.tooltip.show {
	opacity: .9
}

.tooltip.bs-tether-element-attached-bottom, .tooltip.tooltip-top {
	padding: 5px 0;
	margin-top: -3px
}

.tooltip.bs-tether-element-attached-bottom .tooltip-inner::before,
	.tooltip.tooltip-top .tooltip-inner::before {
	bottom: 0;
	left: 50%;
	margin-left: -5px;
	content: "";
	border-width: 5px 5px 0;
	border-top-color: #000
}

.tooltip.bs-tether-element-attached-left, .tooltip.tooltip-right {
	padding: 0 5px;
	margin-left: 3px
}

.tooltip.bs-tether-element-attached-left .tooltip-inner::before,
	.tooltip.tooltip-right .tooltip-inner::before {
	top: 50%;
	left: 0;
	margin-top: -5px;
	content: "";
	border-width: 5px 5px 5px 0;
	border-right-color: #000
}

.tooltip.bs-tether-element-attached-top, .tooltip.tooltip-bottom {
	padding: 5px 0;
	margin-top: 3px
}

.tooltip.bs-tether-element-attached-top .tooltip-inner::before, .tooltip.tooltip-bottom .tooltip-inner::before
	{
	top: 0;
	left: 50%;
	margin-left: -5px;
	content: "";
	border-width: 0 5px 5px;
	border-bottom-color: #000
}

.tooltip.bs-tether-element-attached-right, .tooltip.tooltip-left {
	padding: 0 5px;
	margin-left: -3px
}

.tooltip.bs-tether-element-attached-right .tooltip-inner::before,
	.tooltip.tooltip-left .tooltip-inner::before {
	top: 50%;
	right: 0;
	margin-top: -5px;
	content: "";
	border-width: 5px 0 5px 5px;
	border-left-color: #000
}

.tooltip-inner {
	max-width: 200px;
	padding: 3px 8px;
	color: #fff;
	text-align: center;
	background-color: #000;
	border-radius: .25rem
}

.tooltip-inner::before {
	position: absolute;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid
}

.popover {
	position: absolute;
	top: 0;
	left: 0;
	z-index: 1060;
	display: block;
	max-width: 276px;
	padding: 1px;
	font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI",
		Roboto, "Helvetica Neue", Arial, sans-serif;
	font-style: normal;
	font-weight: 400;
	letter-spacing: normal;
	line-break: auto;
	line-height: 1.5;
	text-align: left;
	text-align: start;
	text-decoration: none;
	text-shadow: none;
	text-transform: none;
	white-space: normal;
	word-break: normal;
	word-spacing: normal;
	font-size: .875rem;
	word-wrap: break-word;
	background-color: #fff;
	-webkit-background-clip: padding-box;
	background-clip: padding-box;
	border: 1px solid rgba(0, 0, 0, .2);
	border-radius: .3rem
}

.popover.bs-tether-element-attached-bottom, .popover.popover-top {
	margin-top: -10px
}

.popover.bs-tether-element-attached-bottom::after, .popover.bs-tether-element-attached-bottom::before,
	.popover.popover-top::after, .popover.popover-top::before {
	left: 50%;
	border-bottom-width: 0
}

.popover.bs-tether-element-attached-bottom::before, .popover.popover-top::before
	{
	bottom: -11px;
	margin-left: -11px;
	border-top-color: rgba(0, 0, 0, .25)
}

.popover.bs-tether-element-attached-bottom::after, .popover.popover-top::after
	{
	bottom: -10px;
	margin-left: -10px;
	border-top-color: #fff
}

.popover.bs-tether-element-attached-left, .popover.popover-right {
	margin-left: 10px
}

.popover.bs-tether-element-attached-left::after, .popover.bs-tether-element-attached-left::before,
	.popover.popover-right::after, .popover.popover-right::before {
	top: 50%;
	border-left-width: 0
}

.popover.bs-tether-element-attached-left::before, .popover.popover-right::before
	{
	left: -11px;
	margin-top: -11px;
	border-right-color: rgba(0, 0, 0, .25)
}

.popover.bs-tether-element-attached-left::after, .popover.popover-right::after
	{
	left: -10px;
	margin-top: -10px;
	border-right-color: #fff
}

.popover.bs-tether-element-attached-top, .popover.popover-bottom {
	margin-top: 10px
}

.popover.bs-tether-element-attached-top::after, .popover.bs-tether-element-attached-top::before,
	.popover.popover-bottom::after, .popover.popover-bottom::before {
	left: 50%;
	border-top-width: 0
}

.popover.bs-tether-element-attached-top::before, .popover.popover-bottom::before
	{
	top: -11px;
	margin-left: -11px;
	border-bottom-color: rgba(0, 0, 0, .25)
}

.popover.bs-tether-element-attached-top::after, .popover.popover-bottom::after
	{
	top: -10px;
	margin-left: -10px;
	border-bottom-color: #f7f7f7
}

.popover.bs-tether-element-attached-top .popover-title::before, .popover.popover-bottom .popover-title::before
	{
	position: absolute;
	top: 0;
	left: 50%;
	display: block;
	width: 20px;
	margin-left: -10px;
	content: "";
	border-bottom: 1px solid #f7f7f7
}

.popover.bs-tether-element-attached-right, .popover.popover-left {
	margin-left: -10px
}

.popover.bs-tether-element-attached-right::after, .popover.bs-tether-element-attached-right::before,
	.popover.popover-left::after, .popover.popover-left::before {
	top: 50%;
	border-right-width: 0
}

.popover.bs-tether-element-attached-right::before, .popover.popover-left::before
	{
	right: -11px;
	margin-top: -11px;
	border-left-color: rgba(0, 0, 0, .25)
}

.popover.bs-tether-element-attached-right::after, .popover.popover-left::after
	{
	right: -10px;
	margin-top: -10px;
	border-left-color: #fff
}

.popover-title {
	padding: 8px 14px;
	margin-bottom: 0;
	font-size: 1rem;
	background-color: #f7f7f7;
	border-bottom: 1px solid #ebebeb;
	border-top-right-radius: calc(.3rem - 1px);
	border-top-left-radius: calc(.3rem - 1px)
}

.popover-title:empty {
	display: none
}

.popover-content {
	padding: 9px 14px
}

.popover::after, .popover::before {
	position: absolute;
	display: block;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid
}

.popover::before {
	content: "";
	border-width: 11px
}

.popover::after {
	content: "";
	border-width: 10px
}

.carousel {
	position: relative
}

.carousel-inner {
	position: relative;
	width: 100%;
	overflow: hidden
}

.carousel-item {
	position: relative;
	display: none;
	width: 100%
}

@media ( -webkit-transform-3d ) {
	.carousel-item {
		-webkit-transition: -webkit-transform .6s ease-in-out;
		transition: -webkit-transform .6s ease-in-out;
		-o-transition: -o-transform .6s ease-in-out;
		transition: transform .6s ease-in-out;
		transition: transform .6s ease-in-out, -webkit-transform .6s ease-in-out,
			-o-transform .6s ease-in-out;
		-webkit-backface-visibility: hidden;
		backface-visibility: hidden;
		-webkit-perspective: 1000px;
		perspective: 1000px
	}
}

@
supports ((-webkit-transform:translate3d (0, 0,0)) or (transform:translate3d
	(0, 0,0))){.carousel-item {
	-webkit-transition: -webkit-transform .6s ease-in-out;
	transition: -webkit-transform .6s ease-in-out;
	-o-transition: -o-transform .6s ease-in-out;
	transition: transform .6s ease-in-out;
	transition: transform .6s ease-in-out, -webkit-transform .6s ease-in-out,
		-o-transform .6s ease-in-out;
	-webkit-backface-visibility: hidden;
	backface-visibility: hidden;
	-webkit-perspective: 1000px;
	perspective: 1000px
}

}
.carousel-item-next, .carousel-item-prev, .carousel-item.active {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex
}

.carousel-item-next, .carousel-item-prev {
	position: absolute;
	top: 0
}

@media ( -webkit-transform-3d ) {
	.carousel-item-next.carousel-item-left, .carousel-item-prev.carousel-item-right
		{
		-webkit-transform: translate3d(0, 0, 0);
		transform: translate3d(0, 0, 0)
	}
	.active.carousel-item-right, .carousel-item-next {
		-webkit-transform: translate3d(100%, 0, 0);
		transform: translate3d(100%, 0, 0)
	}
	.active.carousel-item-left, .carousel-item-prev {
		-webkit-transform: translate3d(-100%, 0, 0);
		transform: translate3d(-100%, 0, 0)
	}
}

@
supports ((-webkit-transform:translate3d (0, 0,0)) or (transform:translate3d
	(0, 0,0))){.carousel-item-next.carousel-item-left, .carousel-item-prev.carousel-item-right
	{
	-webkit-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0)
}

.active.carousel-item-right, .carousel-item-next {
	-webkit-transform: translate3d(100%, 0, 0);
	transform: translate3d(100%, 0, 0)
}

.active.carousel-item-left, .carousel-item-prev {
	-webkit-transform: translate3d(-100%, 0, 0);
	transform: translate3d(-100%, 0, 0)
}

}
.carousel-control-next, .carousel-control-prev {
	position: absolute;
	top: 0;
	bottom: 0;
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-align: center;
	-webkit-align-items: center;
	-ms-flex-align: center;
	align-items: center;
	-webkit-box-pack: center;
	-webkit-justify-content: center;
	-ms-flex-pack: center;
	justify-content: center;
	width: 15%;
	color: #fff;
	text-align: center;
	opacity: .5
}

.carousel-control-next:focus, .carousel-control-next:hover,
	.carousel-control-prev:focus, .carousel-control-prev:hover {
	color: #fff;
	text-decoration: none;
	outline: 0;
	opacity: .9
}

.carousel-control-prev {
	left: 0
}

.carousel-control-next {
	right: 0
}

.carousel-control-next-icon, .carousel-control-prev-icon {
	display: inline-block;
	width: 20px;
	height: 20px;
	background: transparent no-repeat center center;
	-webkit-background-size: 100% 100%;
	background-size: 100% 100%
}

.carousel-control-prev-icon {
	background-image:
		url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23fff' viewBox='0 0 8 8'%3E%3Cpath d='M4 0l-4 4 4 4 1.5-1.5-2.5-2.5 2.5-2.5-1.5-1.5z'/%3E%3C/svg%3E")
}

.carousel-control-next-icon {
	background-image:
		url("data:image/svg+xml;charset=utf8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='%23fff' viewBox='0 0 8 8'%3E%3Cpath d='M1.5 0l-1.5 1.5 2.5 2.5-2.5 2.5 1.5 1.5 4-4-4-4z'/%3E%3C/svg%3E")
}

.carousel-indicators {
	position: absolute;
	right: 0;
	bottom: 10px;
	left: 0;
	z-index: 15;
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-box-pack: center;
	-webkit-justify-content: center;
	-ms-flex-pack: center;
	justify-content: center;
	padding-left: 0;
	margin-right: 15%;
	margin-left: 15%;
	list-style: none
}

.carousel-indicators li {
	position: relative;
	-webkit-box-flex: 1;
	-webkit-flex: 1 0 auto;
	-ms-flex: 1 0 auto;
	flex: 1 0 auto;
	max-width: 30px;
	height: 3px;
	margin-right: 3px;
	margin-left: 3px;
	text-indent: -999px;
	cursor: pointer;
	background-color: rgba(255, 255, 255, .5)
}

.carousel-indicators li::before {
	position: absolute;
	top: -10px;
	left: 0;
	display: inline-block;
	width: 100%;
	height: 10px;
	content: ""
}

.carousel-indicators li::after {
	position: absolute;
	bottom: -10px;
	left: 0;
	display: inline-block;
	width: 100%;
	height: 10px;
	content: ""
}

.carousel-indicators .active {
	background-color: #fff
}

.carousel-caption {
	position: absolute;
	right: 15%;
	bottom: 20px;
	left: 15%;
	z-index: 10;
	padding-top: 20px;
	padding-bottom: 20px;
	color: #fff;
	text-align: center
}

.align-baseline {
	vertical-align: baseline !important
}

.align-top {
	vertical-align: top !important
}

.align-middle {
	vertical-align: middle !important
}

.align-bottom {
	vertical-align: bottom !important
}

.align-text-bottom {
	vertical-align: text-bottom !important
}

.align-text-top {
	vertical-align: text-top !important
}

.bg-faded {
	background-color: #f7f7f7
}

.bg-primary {
	background-color: #0275d8 !important
}

a.bg-primary:focus, a.bg-primary:hover {
	background-color: #025aa5 !important
}

.bg-success {
	background-color: #5cb85c !important
}

a.bg-success:focus, a.bg-success:hover {
	background-color: #449d44 !important
}

.bg-info {
	background-color: #5bc0de !important
}

a.bg-info:focus, a.bg-info:hover {
	background-color: #31b0d5 !important
}

.bg-warning {
	background-color: #f0ad4e !important
}

a.bg-warning:focus, a.bg-warning:hover {
	background-color: #ec971f !important
}

.bg-danger {
	background-color: #d9534f !important
}

a.bg-danger:focus, a.bg-danger:hover {
	background-color: #c9302c !important
}

.bg-inverse {
	background-color: #292b2c !important
}

a.bg-inverse:focus, a.bg-inverse:hover {
	background-color: #101112 !important
}

.border-0 {
	border: 0 !important
}

.border-top-0 {
	border-top: 0 !important
}

.border-right-0 {
	border-right: 0 !important
}

.border-bottom-0 {
	border-bottom: 0 !important
}

.border-left-0 {
	border-left: 0 !important
}

.rounded {
	border-radius: .25rem
}

.rounded-top {
	border-top-right-radius: .25rem;
	border-top-left-radius: .25rem
}

.rounded-right {
	border-bottom-right-radius: .25rem;
	border-top-right-radius: .25rem
}

.rounded-bottom {
	border-bottom-right-radius: .25rem;
	border-bottom-left-radius: .25rem
}

.rounded-left {
	border-bottom-left-radius: .25rem;
	border-top-left-radius: .25rem
}

.rounded-circle {
	border-radius: 50%
}

.rounded-0 {
	border-radius: 0
}

.clearfix::after {
	display: block;
	content: "";
	clear: both
}

.d-none {
	display: none !important
}

.d-inline {
	display: inline !important
}

.d-inline-block {
	display: inline-block !important
}

.d-block {
	display: block !important
}

.d-table {
	display: table !important
}

.d-table-cell {
	display: table-cell !important
}

.d-flex {
	display: -webkit-box !important;
	display: -webkit-flex !important;
	display: -ms-flexbox !important;
	display: flex !important
}

.d-inline-flex {
	display: -webkit-inline-box !important;
	display: -webkit-inline-flex !important;
	display: -ms-inline-flexbox !important;
	display: inline-flex !important
}

@media ( min-width :576px) {
	.d-sm-none {
		display: none !important
	}
	.d-sm-inline {
		display: inline !important
	}
	.d-sm-inline-block {
		display: inline-block !important
	}
	.d-sm-block {
		display: block !important
	}
	.d-sm-table {
		display: table !important
	}
	.d-sm-table-cell {
		display: table-cell !important
	}
	.d-sm-flex {
		display: -webkit-box !important;
		display: -webkit-flex !important;
		display: -ms-flexbox !important;
		display: flex !important
	}
	.d-sm-inline-flex {
		display: -webkit-inline-box !important;
		display: -webkit-inline-flex !important;
		display: -ms-inline-flexbox !important;
		display: inline-flex !important
	}
}

@media ( min-width :768px) {
	.d-md-none {
		display: none !important
	}
	.d-md-inline {
		display: inline !important
	}
	.d-md-inline-block {
		display: inline-block !important
	}
	.d-md-block {
		display: block !important
	}
	.d-md-table {
		display: table !important
	}
	.d-md-table-cell {
		display: table-cell !important
	}
	.d-md-flex {
		display: -webkit-box !important;
		display: -webkit-flex !important;
		display: -ms-flexbox !important;
		display: flex !important
	}
	.d-md-inline-flex {
		display: -webkit-inline-box !important;
		display: -webkit-inline-flex !important;
		display: -ms-inline-flexbox !important;
		display: inline-flex !important
	}
}

@media ( min-width :992px) {
	.d-lg-none {
		display: none !important
	}
	.d-lg-inline {
		display: inline !important
	}
	.d-lg-inline-block {
		display: inline-block !important
	}
	.d-lg-block {
		display: block !important
	}
	.d-lg-table {
		display: table !important
	}
	.d-lg-table-cell {
		display: table-cell !important
	}
	.d-lg-flex {
		display: -webkit-box !important;
		display: -webkit-flex !important;
		display: -ms-flexbox !important;
		display: flex !important
	}
	.d-lg-inline-flex {
		display: -webkit-inline-box !important;
		display: -webkit-inline-flex !important;
		display: -ms-inline-flexbox !important;
		display: inline-flex !important
	}
}

@media ( min-width :1200px) {
	.d-xl-none {
		display: none !important
	}
	.d-xl-inline {
		display: inline !important
	}
	.d-xl-inline-block {
		display: inline-block !important
	}
	.d-xl-block {
		display: block !important
	}
	.d-xl-table {
		display: table !important
	}
	.d-xl-table-cell {
		display: table-cell !important
	}
	.d-xl-flex {
		display: -webkit-box !important;
		display: -webkit-flex !important;
		display: -ms-flexbox !important;
		display: flex !important
	}
	.d-xl-inline-flex {
		display: -webkit-inline-box !important;
		display: -webkit-inline-flex !important;
		display: -ms-inline-flexbox !important;
		display: inline-flex !important
	}
}

.flex-first {
	-webkit-box-ordinal-group: 0;
	-webkit-order: -1;
	-ms-flex-order: -1;
	order: -1
}

.flex-last {
	-webkit-box-ordinal-group: 2;
	-webkit-order: 1;
	-ms-flex-order: 1;
	order: 1
}

.flex-unordered {
	-webkit-box-ordinal-group: 1;
	-webkit-order: 0;
	-ms-flex-order: 0;
	order: 0
}

.flex-row {
	-webkit-box-orient: horizontal !important;
	-webkit-box-direction: normal !important;
	-webkit-flex-direction: row !important;
	-ms-flex-direction: row !important;
	flex-direction: row !important
}

.flex-column {
	-webkit-box-orient: vertical !important;
	-webkit-box-direction: normal !important;
	-webkit-flex-direction: column !important;
	-ms-flex-direction: column !important;
	flex-direction: column !important
}

.flex-row-reverse {
	-webkit-box-orient: horizontal !important;
	-webkit-box-direction: reverse !important;
	-webkit-flex-direction: row-reverse !important;
	-ms-flex-direction: row-reverse !important;
	flex-direction: row-reverse !important
}

.flex-column-reverse {
	-webkit-box-orient: vertical !important;
	-webkit-box-direction: reverse !important;
	-webkit-flex-direction: column-reverse !important;
	-ms-flex-direction: column-reverse !important;
	flex-direction: column-reverse !important
}

.flex-wrap {
	-webkit-flex-wrap: wrap !important;
	-ms-flex-wrap: wrap !important;
	flex-wrap: wrap !important
}

.flex-nowrap {
	-webkit-flex-wrap: nowrap !important;
	-ms-flex-wrap: nowrap !important;
	flex-wrap: nowrap !important
}

.flex-wrap-reverse {
	-webkit-flex-wrap: wrap-reverse !important;
	-ms-flex-wrap: wrap-reverse !important;
	flex-wrap: wrap-reverse !important
}

.justify-content-start {
	-webkit-box-pack: start !important;
	-webkit-justify-content: flex-start !important;
	-ms-flex-pack: start !important;
	justify-content: flex-start !important
}

.justify-content-end {
	-webkit-box-pack: end !important;
	-webkit-justify-content: flex-end !important;
	-ms-flex-pack: end !important;
	justify-content: flex-end !important
}

.justify-content-center {
	-webkit-box-pack: center !important;
	-webkit-justify-content: center !important;
	-ms-flex-pack: center !important;
	justify-content: center !important
}

.justify-content-between {
	-webkit-box-pack: justify !important;
	-webkit-justify-content: space-between !important;
	-ms-flex-pack: justify !important;
	justify-content: space-between !important
}

.justify-content-around {
	-webkit-justify-content: space-around !important;
	-ms-flex-pack: distribute !important;
	justify-content: space-around !important
}

.align-items-start {
	-webkit-box-align: start !important;
	-webkit-align-items: flex-start !important;
	-ms-flex-align: start !important;
	align-items: flex-start !important
}

.align-items-end {
	-webkit-box-align: end !important;
	-webkit-align-items: flex-end !important;
	-ms-flex-align: end !important;
	align-items: flex-end !important
}

.align-items-center {
	-webkit-box-align: center !important;
	-webkit-align-items: center !important;
	-ms-flex-align: center !important;
	align-items: center !important
}

.align-items-baseline {
	-webkit-box-align: baseline !important;
	-webkit-align-items: baseline !important;
	-ms-flex-align: baseline !important;
	align-items: baseline !important
}

.align-items-stretch {
	-webkit-box-align: stretch !important;
	-webkit-align-items: stretch !important;
	-ms-flex-align: stretch !important;
	align-items: stretch !important
}

.align-content-start {
	-webkit-align-content: flex-start !important;
	-ms-flex-line-pack: start !important;
	align-content: flex-start !important
}

.align-content-end {
	-webkit-align-content: flex-end !important;
	-ms-flex-line-pack: end !important;
	align-content: flex-end !important
}

.align-content-center {
	-webkit-align-content: center !important;
	-ms-flex-line-pack: center !important;
	align-content: center !important
}

.align-content-between {
	-webkit-align-content: space-between !important;
	-ms-flex-line-pack: justify !important;
	align-content: space-between !important
}

.align-content-around {
	-webkit-align-content: space-around !important;
	-ms-flex-line-pack: distribute !important;
	align-content: space-around !important
}

.align-content-stretch {
	-webkit-align-content: stretch !important;
	-ms-flex-line-pack: stretch !important;
	align-content: stretch !important
}

.align-self-auto {
	-webkit-align-self: auto !important;
	-ms-flex-item-align: auto !important;
	-ms-grid-row-align: auto !important;
	align-self: auto !important
}

.align-self-start {
	-webkit-align-self: flex-start !important;
	-ms-flex-item-align: start !important;
	align-self: flex-start !important
}

.align-self-end {
	-webkit-align-self: flex-end !important;
	-ms-flex-item-align: end !important;
	align-self: flex-end !important
}

.align-self-center {
	-webkit-align-self: center !important;
	-ms-flex-item-align: center !important;
	-ms-grid-row-align: center !important;
	align-self: center !important
}

.align-self-baseline {
	-webkit-align-self: baseline !important;
	-ms-flex-item-align: baseline !important;
	align-self: baseline !important
}

.align-self-stretch {
	-webkit-align-self: stretch !important;
	-ms-flex-item-align: stretch !important;
	-ms-grid-row-align: stretch !important;
	align-self: stretch !important
}

@media ( min-width :576px) {
	.flex-sm-first {
		-webkit-box-ordinal-group: 0;
		-webkit-order: -1;
		-ms-flex-order: -1;
		order: -1
	}
	.flex-sm-last {
		-webkit-box-ordinal-group: 2;
		-webkit-order: 1;
		-ms-flex-order: 1;
		order: 1
	}
	.flex-sm-unordered {
		-webkit-box-ordinal-group: 1;
		-webkit-order: 0;
		-ms-flex-order: 0;
		order: 0
	}
	.flex-sm-row {
		-webkit-box-orient: horizontal !important;
		-webkit-box-direction: normal !important;
		-webkit-flex-direction: row !important;
		-ms-flex-direction: row !important;
		flex-direction: row !important
	}
	.flex-sm-column {
		-webkit-box-orient: vertical !important;
		-webkit-box-direction: normal !important;
		-webkit-flex-direction: column !important;
		-ms-flex-direction: column !important;
		flex-direction: column !important
	}
	.flex-sm-row-reverse {
		-webkit-box-orient: horizontal !important;
		-webkit-box-direction: reverse !important;
		-webkit-flex-direction: row-reverse !important;
		-ms-flex-direction: row-reverse !important;
		flex-direction: row-reverse !important
	}
	.flex-sm-column-reverse {
		-webkit-box-orient: vertical !important;
		-webkit-box-direction: reverse !important;
		-webkit-flex-direction: column-reverse !important;
		-ms-flex-direction: column-reverse !important;
		flex-direction: column-reverse !important
	}
	.flex-sm-wrap {
		-webkit-flex-wrap: wrap !important;
		-ms-flex-wrap: wrap !important;
		flex-wrap: wrap !important
	}
	.flex-sm-nowrap {
		-webkit-flex-wrap: nowrap !important;
		-ms-flex-wrap: nowrap !important;
		flex-wrap: nowrap !important
	}
	.flex-sm-wrap-reverse {
		-webkit-flex-wrap: wrap-reverse !important;
		-ms-flex-wrap: wrap-reverse !important;
		flex-wrap: wrap-reverse !important
	}
	.justify-content-sm-start {
		-webkit-box-pack: start !important;
		-webkit-justify-content: flex-start !important;
		-ms-flex-pack: start !important;
		justify-content: flex-start !important
	}
	.justify-content-sm-end {
		-webkit-box-pack: end !important;
		-webkit-justify-content: flex-end !important;
		-ms-flex-pack: end !important;
		justify-content: flex-end !important
	}
	.justify-content-sm-center {
		-webkit-box-pack: center !important;
		-webkit-justify-content: center !important;
		-ms-flex-pack: center !important;
		justify-content: center !important
	}
	.justify-content-sm-between {
		-webkit-box-pack: justify !important;
		-webkit-justify-content: space-between !important;
		-ms-flex-pack: justify !important;
		justify-content: space-between !important
	}
	.justify-content-sm-around {
		-webkit-justify-content: space-around !important;
		-ms-flex-pack: distribute !important;
		justify-content: space-around !important
	}
	.align-items-sm-start {
		-webkit-box-align: start !important;
		-webkit-align-items: flex-start !important;
		-ms-flex-align: start !important;
		align-items: flex-start !important
	}
	.align-items-sm-end {
		-webkit-box-align: end !important;
		-webkit-align-items: flex-end !important;
		-ms-flex-align: end !important;
		align-items: flex-end !important
	}
	.align-items-sm-center {
		-webkit-box-align: center !important;
		-webkit-align-items: center !important;
		-ms-flex-align: center !important;
		align-items: center !important
	}
	.align-items-sm-baseline {
		-webkit-box-align: baseline !important;
		-webkit-align-items: baseline !important;
		-ms-flex-align: baseline !important;
		align-items: baseline !important
	}
	.align-items-sm-stretch {
		-webkit-box-align: stretch !important;
		-webkit-align-items: stretch !important;
		-ms-flex-align: stretch !important;
		align-items: stretch !important
	}
	.align-content-sm-start {
		-webkit-align-content: flex-start !important;
		-ms-flex-line-pack: start !important;
		align-content: flex-start !important
	}
	.align-content-sm-end {
		-webkit-align-content: flex-end !important;
		-ms-flex-line-pack: end !important;
		align-content: flex-end !important
	}
	.align-content-sm-center {
		-webkit-align-content: center !important;
		-ms-flex-line-pack: center !important;
		align-content: center !important
	}
	.align-content-sm-between {
		-webkit-align-content: space-between !important;
		-ms-flex-line-pack: justify !important;
		align-content: space-between !important
	}
	.align-content-sm-around {
		-webkit-align-content: space-around !important;
		-ms-flex-line-pack: distribute !important;
		align-content: space-around !important
	}
	.align-content-sm-stretch {
		-webkit-align-content: stretch !important;
		-ms-flex-line-pack: stretch !important;
		align-content: stretch !important
	}
	.align-self-sm-auto {
		-webkit-align-self: auto !important;
		-ms-flex-item-align: auto !important;
		-ms-grid-row-align: auto !important;
		align-self: auto !important
	}
	.align-self-sm-start {
		-webkit-align-self: flex-start !important;
		-ms-flex-item-align: start !important;
		align-self: flex-start !important
	}
	.align-self-sm-end {
		-webkit-align-self: flex-end !important;
		-ms-flex-item-align: end !important;
		align-self: flex-end !important
	}
	.align-self-sm-center {
		-webkit-align-self: center !important;
		-ms-flex-item-align: center !important;
		-ms-grid-row-align: center !important;
		align-self: center !important
	}
	.align-self-sm-baseline {
		-webkit-align-self: baseline !important;
		-ms-flex-item-align: baseline !important;
		align-self: baseline !important
	}
	.align-self-sm-stretch {
		-webkit-align-self: stretch !important;
		-ms-flex-item-align: stretch !important;
		-ms-grid-row-align: stretch !important;
		align-self: stretch !important
	}
}

@media ( min-width :768px) {
	.flex-md-first {
		-webkit-box-ordinal-group: 0;
		-webkit-order: -1;
		-ms-flex-order: -1;
		order: -1
	}
	.flex-md-last {
		-webkit-box-ordinal-group: 2;
		-webkit-order: 1;
		-ms-flex-order: 1;
		order: 1
	}
	.flex-md-unordered {
		-webkit-box-ordinal-group: 1;
		-webkit-order: 0;
		-ms-flex-order: 0;
		order: 0
	}
	.flex-md-row {
		-webkit-box-orient: horizontal !important;
		-webkit-box-direction: normal !important;
		-webkit-flex-direction: row !important;
		-ms-flex-direction: row !important;
		flex-direction: row !important
	}
	.flex-md-column {
		-webkit-box-orient: vertical !important;
		-webkit-box-direction: normal !important;
		-webkit-flex-direction: column !important;
		-ms-flex-direction: column !important;
		flex-direction: column !important
	}
	.flex-md-row-reverse {
		-webkit-box-orient: horizontal !important;
		-webkit-box-direction: reverse !important;
		-webkit-flex-direction: row-reverse !important;
		-ms-flex-direction: row-reverse !important;
		flex-direction: row-reverse !important
	}
	.flex-md-column-reverse {
		-webkit-box-orient: vertical !important;
		-webkit-box-direction: reverse !important;
		-webkit-flex-direction: column-reverse !important;
		-ms-flex-direction: column-reverse !important;
		flex-direction: column-reverse !important
	}
	.flex-md-wrap {
		-webkit-flex-wrap: wrap !important;
		-ms-flex-wrap: wrap !important;
		flex-wrap: wrap !important
	}
	.flex-md-nowrap {
		-webkit-flex-wrap: nowrap !important;
		-ms-flex-wrap: nowrap !important;
		flex-wrap: nowrap !important
	}
	.flex-md-wrap-reverse {
		-webkit-flex-wrap: wrap-reverse !important;
		-ms-flex-wrap: wrap-reverse !important;
		flex-wrap: wrap-reverse !important
	}
	.justify-content-md-start {
		-webkit-box-pack: start !important;
		-webkit-justify-content: flex-start !important;
		-ms-flex-pack: start !important;
		justify-content: flex-start !important
	}
	.justify-content-md-end {
		-webkit-box-pack: end !important;
		-webkit-justify-content: flex-end !important;
		-ms-flex-pack: end !important;
		justify-content: flex-end !important
	}
	.justify-content-md-center {
		-webkit-box-pack: center !important;
		-webkit-justify-content: center !important;
		-ms-flex-pack: center !important;
		justify-content: center !important
	}
	.justify-content-md-between {
		-webkit-box-pack: justify !important;
		-webkit-justify-content: space-between !important;
		-ms-flex-pack: justify !important;
		justify-content: space-between !important
	}
	.justify-content-md-around {
		-webkit-justify-content: space-around !important;
		-ms-flex-pack: distribute !important;
		justify-content: space-around !important
	}
	.align-items-md-start {
		-webkit-box-align: start !important;
		-webkit-align-items: flex-start !important;
		-ms-flex-align: start !important;
		align-items: flex-start !important
	}
	.align-items-md-end {
		-webkit-box-align: end !important;
		-webkit-align-items: flex-end !important;
		-ms-flex-align: end !important;
		align-items: flex-end !important
	}
	.align-items-md-center {
		-webkit-box-align: center !important;
		-webkit-align-items: center !important;
		-ms-flex-align: center !important;
		align-items: center !important
	}
	.align-items-md-baseline {
		-webkit-box-align: baseline !important;
		-webkit-align-items: baseline !important;
		-ms-flex-align: baseline !important;
		align-items: baseline !important
	}
	.align-items-md-stretch {
		-webkit-box-align: stretch !important;
		-webkit-align-items: stretch !important;
		-ms-flex-align: stretch !important;
		align-items: stretch !important
	}
	.align-content-md-start {
		-webkit-align-content: flex-start !important;
		-ms-flex-line-pack: start !important;
		align-content: flex-start !important
	}
	.align-content-md-end {
		-webkit-align-content: flex-end !important;
		-ms-flex-line-pack: end !important;
		align-content: flex-end !important
	}
	.align-content-md-center {
		-webkit-align-content: center !important;
		-ms-flex-line-pack: center !important;
		align-content: center !important
	}
	.align-content-md-between {
		-webkit-align-content: space-between !important;
		-ms-flex-line-pack: justify !important;
		align-content: space-between !important
	}
	.align-content-md-around {
		-webkit-align-content: space-around !important;
		-ms-flex-line-pack: distribute !important;
		align-content: space-around !important
	}
	.align-content-md-stretch {
		-webkit-align-content: stretch !important;
		-ms-flex-line-pack: stretch !important;
		align-content: stretch !important
	}
	.align-self-md-auto {
		-webkit-align-self: auto !important;
		-ms-flex-item-align: auto !important;
		-ms-grid-row-align: auto !important;
		align-self: auto !important
	}
	.align-self-md-start {
		-webkit-align-self: flex-start !important;
		-ms-flex-item-align: start !important;
		align-self: flex-start !important
	}
	.align-self-md-end {
		-webkit-align-self: flex-end !important;
		-ms-flex-item-align: end !important;
		align-self: flex-end !important
	}
	.align-self-md-center {
		-webkit-align-self: center !important;
		-ms-flex-item-align: center !important;
		-ms-grid-row-align: center !important;
		align-self: center !important
	}
	.align-self-md-baseline {
		-webkit-align-self: baseline !important;
		-ms-flex-item-align: baseline !important;
		align-self: baseline !important
	}
	.align-self-md-stretch {
		-webkit-align-self: stretch !important;
		-ms-flex-item-align: stretch !important;
		-ms-grid-row-align: stretch !important;
		align-self: stretch !important
	}
}

@media ( min-width :992px) {
	.flex-lg-first {
		-webkit-box-ordinal-group: 0;
		-webkit-order: -1;
		-ms-flex-order: -1;
		order: -1
	}
	.flex-lg-last {
		-webkit-box-ordinal-group: 2;
		-webkit-order: 1;
		-ms-flex-order: 1;
		order: 1
	}
	.flex-lg-unordered {
		-webkit-box-ordinal-group: 1;
		-webkit-order: 0;
		-ms-flex-order: 0;
		order: 0
	}
	.flex-lg-row {
		-webkit-box-orient: horizontal !important;
		-webkit-box-direction: normal !important;
		-webkit-flex-direction: row !important;
		-ms-flex-direction: row !important;
		flex-direction: row !important
	}
	.flex-lg-column {
		-webkit-box-orient: vertical !important;
		-webkit-box-direction: normal !important;
		-webkit-flex-direction: column !important;
		-ms-flex-direction: column !important;
		flex-direction: column !important
	}
	.flex-lg-row-reverse {
		-webkit-box-orient: horizontal !important;
		-webkit-box-direction: reverse !important;
		-webkit-flex-direction: row-reverse !important;
		-ms-flex-direction: row-reverse !important;
		flex-direction: row-reverse !important
	}
	.flex-lg-column-reverse {
		-webkit-box-orient: vertical !important;
		-webkit-box-direction: reverse !important;
		-webkit-flex-direction: column-reverse !important;
		-ms-flex-direction: column-reverse !important;
		flex-direction: column-reverse !important
	}
	.flex-lg-wrap {
		-webkit-flex-wrap: wrap !important;
		-ms-flex-wrap: wrap !important;
		flex-wrap: wrap !important
	}
	.flex-lg-nowrap {
		-webkit-flex-wrap: nowrap !important;
		-ms-flex-wrap: nowrap !important;
		flex-wrap: nowrap !important
	}
	.flex-lg-wrap-reverse {
		-webkit-flex-wrap: wrap-reverse !important;
		-ms-flex-wrap: wrap-reverse !important;
		flex-wrap: wrap-reverse !important
	}
	.justify-content-lg-start {
		-webkit-box-pack: start !important;
		-webkit-justify-content: flex-start !important;
		-ms-flex-pack: start !important;
		justify-content: flex-start !important
	}
	.justify-content-lg-end {
		-webkit-box-pack: end !important;
		-webkit-justify-content: flex-end !important;
		-ms-flex-pack: end !important;
		justify-content: flex-end !important
	}
	.justify-content-lg-center {
		-webkit-box-pack: center !important;
		-webkit-justify-content: center !important;
		-ms-flex-pack: center !important;
		justify-content: center !important
	}
	.justify-content-lg-between {
		-webkit-box-pack: justify !important;
		-webkit-justify-content: space-between !important;
		-ms-flex-pack: justify !important;
		justify-content: space-between !important
	}
	.justify-content-lg-around {
		-webkit-justify-content: space-around !important;
		-ms-flex-pack: distribute !important;
		justify-content: space-around !important
	}
	.align-items-lg-start {
		-webkit-box-align: start !important;
		-webkit-align-items: flex-start !important;
		-ms-flex-align: start !important;
		align-items: flex-start !important
	}
	.align-items-lg-end {
		-webkit-box-align: end !important;
		-webkit-align-items: flex-end !important;
		-ms-flex-align: end !important;
		align-items: flex-end !important
	}
	.align-items-lg-center {
		-webkit-box-align: center !important;
		-webkit-align-items: center !important;
		-ms-flex-align: center !important;
		align-items: center !important
	}
	.align-items-lg-baseline {
		-webkit-box-align: baseline !important;
		-webkit-align-items: baseline !important;
		-ms-flex-align: baseline !important;
		align-items: baseline !important
	}
	.align-items-lg-stretch {
		-webkit-box-align: stretch !important;
		-webkit-align-items: stretch !important;
		-ms-flex-align: stretch !important;
		align-items: stretch !important
	}
	.align-content-lg-start {
		-webkit-align-content: flex-start !important;
		-ms-flex-line-pack: start !important;
		align-content: flex-start !important
	}
	.align-content-lg-end {
		-webkit-align-content: flex-end !important;
		-ms-flex-line-pack: end !important;
		align-content: flex-end !important
	}
	.align-content-lg-center {
		-webkit-align-content: center !important;
		-ms-flex-line-pack: center !important;
		align-content: center !important
	}
	.align-content-lg-between {
		-webkit-align-content: space-between !important;
		-ms-flex-line-pack: justify !important;
		align-content: space-between !important
	}
	.align-content-lg-around {
		-webkit-align-content: space-around !important;
		-ms-flex-line-pack: distribute !important;
		align-content: space-around !important
	}
	.align-content-lg-stretch {
		-webkit-align-content: stretch !important;
		-ms-flex-line-pack: stretch !important;
		align-content: stretch !important
	}
	.align-self-lg-auto {
		-webkit-align-self: auto !important;
		-ms-flex-item-align: auto !important;
		-ms-grid-row-align: auto !important;
		align-self: auto !important
	}
	.align-self-lg-start {
		-webkit-align-self: flex-start !important;
		-ms-flex-item-align: start !important;
		align-self: flex-start !important
	}
	.align-self-lg-end {
		-webkit-align-self: flex-end !important;
		-ms-flex-item-align: end !important;
		align-self: flex-end !important
	}
	.align-self-lg-center {
		-webkit-align-self: center !important;
		-ms-flex-item-align: center !important;
		-ms-grid-row-align: center !important;
		align-self: center !important
	}
	.align-self-lg-baseline {
		-webkit-align-self: baseline !important;
		-ms-flex-item-align: baseline !important;
		align-self: baseline !important
	}
	.align-self-lg-stretch {
		-webkit-align-self: stretch !important;
		-ms-flex-item-align: stretch !important;
		-ms-grid-row-align: stretch !important;
		align-self: stretch !important
	}
}

@media ( min-width :1200px) {
	.flex-xl-first {
		-webkit-box-ordinal-group: 0;
		-webkit-order: -1;
		-ms-flex-order: -1;
		order: -1
	}
	.flex-xl-last {
		-webkit-box-ordinal-group: 2;
		-webkit-order: 1;
		-ms-flex-order: 1;
		order: 1
	}
	.flex-xl-unordered {
		-webkit-box-ordinal-group: 1;
		-webkit-order: 0;
		-ms-flex-order: 0;
		order: 0
	}
	.flex-xl-row {
		-webkit-box-orient: horizontal !important;
		-webkit-box-direction: normal !important;
		-webkit-flex-direction: row !important;
		-ms-flex-direction: row !important;
		flex-direction: row !important
	}
	.flex-xl-column {
		-webkit-box-orient: vertical !important;
		-webkit-box-direction: normal !important;
		-webkit-flex-direction: column !important;
		-ms-flex-direction: column !important;
		flex-direction: column !important
	}
	.flex-xl-row-reverse {
		-webkit-box-orient: horizontal !important;
		-webkit-box-direction: reverse !important;
		-webkit-flex-direction: row-reverse !important;
		-ms-flex-direction: row-reverse !important;
		flex-direction: row-reverse !important
	}
	.flex-xl-column-reverse {
		-webkit-box-orient: vertical !important;
		-webkit-box-direction: reverse !important;
		-webkit-flex-direction: column-reverse !important;
		-ms-flex-direction: column-reverse !important;
		flex-direction: column-reverse !important
	}
	.flex-xl-wrap {
		-webkit-flex-wrap: wrap !important;
		-ms-flex-wrap: wrap !important;
		flex-wrap: wrap !important
	}
	.flex-xl-nowrap {
		-webkit-flex-wrap: nowrap !important;
		-ms-flex-wrap: nowrap !important;
		flex-wrap: nowrap !important
	}
	.flex-xl-wrap-reverse {
		-webkit-flex-wrap: wrap-reverse !important;
		-ms-flex-wrap: wrap-reverse !important;
		flex-wrap: wrap-reverse !important
	}
	.justify-content-xl-start {
		-webkit-box-pack: start !important;
		-webkit-justify-content: flex-start !important;
		-ms-flex-pack: start !important;
		justify-content: flex-start !important
	}
	.justify-content-xl-end {
		-webkit-box-pack: end !important;
		-webkit-justify-content: flex-end !important;
		-ms-flex-pack: end !important;
		justify-content: flex-end !important
	}
	.justify-content-xl-center {
		-webkit-box-pack: center !important;
		-webkit-justify-content: center !important;
		-ms-flex-pack: center !important;
		justify-content: center !important
	}
	.justify-content-xl-between {
		-webkit-box-pack: justify !important;
		-webkit-justify-content: space-between !important;
		-ms-flex-pack: justify !important;
		justify-content: space-between !important
	}
	.justify-content-xl-around {
		-webkit-justify-content: space-around !important;
		-ms-flex-pack: distribute !important;
		justify-content: space-around !important
	}
	.align-items-xl-start {
		-webkit-box-align: start !important;
		-webkit-align-items: flex-start !important;
		-ms-flex-align: start !important;
		align-items: flex-start !important
	}
	.align-items-xl-end {
		-webkit-box-align: end !important;
		-webkit-align-items: flex-end !important;
		-ms-flex-align: end !important;
		align-items: flex-end !important
	}
	.align-items-xl-center {
		-webkit-box-align: center !important;
		-webkit-align-items: center !important;
		-ms-flex-align: center !important;
		align-items: center !important
	}
	.align-items-xl-baseline {
		-webkit-box-align: baseline !important;
		-webkit-align-items: baseline !important;
		-ms-flex-align: baseline !important;
		align-items: baseline !important
	}
	.align-items-xl-stretch {
		-webkit-box-align: stretch !important;
		-webkit-align-items: stretch !important;
		-ms-flex-align: stretch !important;
		align-items: stretch !important
	}
	.align-content-xl-start {
		-webkit-align-content: flex-start !important;
		-ms-flex-line-pack: start !important;
		align-content: flex-start !important
	}
	.align-content-xl-end {
		-webkit-align-content: flex-end !important;
		-ms-flex-line-pack: end !important;
		align-content: flex-end !important
	}
	.align-content-xl-center {
		-webkit-align-content: center !important;
		-ms-flex-line-pack: center !important;
		align-content: center !important
	}
	.align-content-xl-between {
		-webkit-align-content: space-between !important;
		-ms-flex-line-pack: justify !important;
		align-content: space-between !important
	}
	.align-content-xl-around {
		-webkit-align-content: space-around !important;
		-ms-flex-line-pack: distribute !important;
		align-content: space-around !important
	}
	.align-content-xl-stretch {
		-webkit-align-content: stretch !important;
		-ms-flex-line-pack: stretch !important;
		align-content: stretch !important
	}
	.align-self-xl-auto {
		-webkit-align-self: auto !important;
		-ms-flex-item-align: auto !important;
		-ms-grid-row-align: auto !important;
		align-self: auto !important
	}
	.align-self-xl-start {
		-webkit-align-self: flex-start !important;
		-ms-flex-item-align: start !important;
		align-self: flex-start !important
	}
	.align-self-xl-end {
		-webkit-align-self: flex-end !important;
		-ms-flex-item-align: end !important;
		align-self: flex-end !important
	}
	.align-self-xl-center {
		-webkit-align-self: center !important;
		-ms-flex-item-align: center !important;
		-ms-grid-row-align: center !important;
		align-self: center !important
	}
	.align-self-xl-baseline {
		-webkit-align-self: baseline !important;
		-ms-flex-item-align: baseline !important;
		align-self: baseline !important
	}
	.align-self-xl-stretch {
		-webkit-align-self: stretch !important;
		-ms-flex-item-align: stretch !important;
		-ms-grid-row-align: stretch !important;
		align-self: stretch !important
	}
}

.float-left {
	float: left !important
}

.float-right {
	float: right !important
}

.float-none {
	float: none !important
}

@media ( min-width :576px) {
	.float-sm-left {
		float: left !important
	}
	.float-sm-right {
		float: right !important
	}
	.float-sm-none {
		float: none !important
	}
}

@media ( min-width :768px) {
	.float-md-left {
		float: left !important
	}
	.float-md-right {
		float: right !important
	}
	.float-md-none {
		float: none !important
	}
}

@media ( min-width :992px) {
	.float-lg-left {
		float: left !important
	}
	.float-lg-right {
		float: right !important
	}
	.float-lg-none {
		float: none !important
	}
}

@media ( min-width :1200px) {
	.float-xl-left {
		float: left !important
	}
	.float-xl-right {
		float: right !important
	}
	.float-xl-none {
		float: none !important
	}
}

.fixed-top {
	position: fixed;
	top: 0;
	right: 0;
	left: 0;
	z-index: 1030
}

.fixed-bottom {
	position: fixed;
	right: 0;
	bottom: 0;
	left: 0;
	z-index: 1030
}

.sticky-top {
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	z-index: 1030
}

.sr-only {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0
}

.sr-only-focusable:active, .sr-only-focusable:focus {
	position: static;
	width: auto;
	height: auto;
	margin: 0;
	overflow: visible;
	clip: auto
}

.w-25 {
	width: 25% !important
}

.w-50 {
	width: 50% !important
}

.w-75 {
	width: 75% !important
}

.w-100 {
	width: 100% !important
}

.h-25 {
	height: 25% !important
}

.h-50 {
	height: 50% !important
}

.h-75 {
	height: 75% !important
}

.h-100 {
	height: 100% !important
}

.mw-100 {
	max-width: 100% !important
}

.mh-100 {
	max-height: 100% !important
}

.m-0 {
	margin: 0 0 !important
}

.mt-0 {
	margin-top: 0 !important
}

.mr-0 {
	margin-right: 0 !important
}

.mb-0 {
	margin-bottom: 0 !important
}

.ml-0 {
	margin-left: 0 !important
}

.mx-0 {
	margin-right: 0 !important;
	margin-left: 0 !important
}

.my-0 {
	margin-top: 0 !important;
	margin-bottom: 0 !important
}

.m-1 {
	margin: .25rem .25rem !important
}

.mt-1 {
	margin-top: .25rem !important
}

.mr-1 {
	margin-right: .25rem !important
}

.mb-1 {
	margin-bottom: .25rem !important
}

.ml-1 {
	margin-left: .25rem !important
}

.mx-1 {
	margin-right: .25rem !important;
	margin-left: .25rem !important
}

.my-1 {
	margin-top: .25rem !important;
	margin-bottom: .25rem !important
}

.m-2 {
	margin: .5rem .5rem !important
}

.mt-2 {
	margin-top: .5rem !important
}

.mr-2 {
	margin-right: .5rem !important
}

.mb-2 {
	margin-bottom: .5rem !important
}

.ml-2 {
	margin-left: .5rem !important
}

.mx-2 {
	margin-right: .5rem !important;
	margin-left: .5rem !important
}

.my-2 {
	margin-top: .5rem !important;
	margin-bottom: .5rem !important
}

.m-3 {
	margin: 1rem 1rem !important
}

.mt-3 {
	margin-top: 1rem !important
}

.mr-3 {
	margin-right: 1rem !important
}

.mb-3 {
	margin-bottom: 1rem !important
}

.ml-3 {
	margin-left: 1rem !important
}

.mx-3 {
	margin-right: 1rem !important;
	margin-left: 1rem !important
}

.my-3 {
	margin-top: 1rem !important;
	margin-bottom: 1rem !important
}

.m-4 {
	margin: 1.5rem 1.5rem !important
}

.mt-4 {
	margin-top: 1.5rem !important
}

.mr-4 {
	margin-right: 1.5rem !important
}

.mb-4 {
	margin-bottom: 1.5rem !important
}

.ml-4 {
	margin-left: 1.5rem !important
}

.mx-4 {
	margin-right: 1.5rem !important;
	margin-left: 1.5rem !important
}

.my-4 {
	margin-top: 1.5rem !important;
	margin-bottom: 1.5rem !important
}

.m-5 {
	margin: 3rem 3rem !important
}

.mt-5 {
	margin-top: 3rem !important
}

.mr-5 {
	margin-right: 3rem !important
}

.mb-5 {
	margin-bottom: 3rem !important
}

.ml-5 {
	margin-left: 3rem !important
}

.mx-5 {
	margin-right: 3rem !important;
	margin-left: 3rem !important
}

.my-5 {
	margin-top: 3rem !important;
	margin-bottom: 3rem !important
}

.p-0 {
	padding: 0 0 !important
}

.pt-0 {
	padding-top: 0 !important
}

.pr-0 {
	padding-right: 0 !important
}

.pb-0 {
	padding-bottom: 0 !important
}

.pl-0 {
	padding-left: 0 !important
}

.px-0 {
	padding-right: 0 !important;
	padding-left: 0 !important
}

.py-0 {
	padding-top: 0 !important;
	padding-bottom: 0 !important
}

.p-1 {
	padding: .25rem .25rem !important
}

.pt-1 {
	padding-top: .25rem !important
}

.pr-1 {
	padding-right: .25rem !important
}

.pb-1 {
	padding-bottom: .25rem !important
}

.pl-1 {
	padding-left: .25rem !important
}

.px-1 {
	padding-right: .25rem !important;
	padding-left: .25rem !important
}

.py-1 {
	padding-top: .25rem !important;
	padding-bottom: .25rem !important
}

.p-2 {
	padding: .5rem .5rem !important
}

.pt-2 {
	padding-top: .5rem !important
}

.pr-2 {
	padding-right: .5rem !important
}

.pb-2 {
	padding-bottom: .5rem !important
}

.pl-2 {
	padding-left: .5rem !important
}

.px-2 {
	padding-right: .5rem !important;
	padding-left: .5rem !important
}

.py-2 {
	padding-top: .5rem !important;
	padding-bottom: .5rem !important
}

.p-3 {
	padding: 1rem 1rem !important
}

.pt-3 {
	padding-top: 1rem !important
}

.pr-3 {
	padding-right: 1rem !important
}

.pb-3 {
	padding-bottom: 1rem !important
}

.pl-3 {
	padding-left: 1rem !important
}

.px-3 {
	padding-right: 1rem !important;
	padding-left: 1rem !important
}

.py-3 {
	padding-top: 1rem !important;
	padding-bottom: 1rem !important
}

.p-4 {
	padding: 1.5rem 1.5rem !important
}

.pt-4 {
	padding-top: 1.5rem !important
}

.pr-4 {
	padding-right: 1.5rem !important
}

.pb-4 {
	padding-bottom: 1.5rem !important
}

.pl-4 {
	padding-left: 1.5rem !important
}

.px-4 {
	padding-right: 1.5rem !important;
	padding-left: 1.5rem !important
}

.py-4 {
	padding-top: 1.5rem !important;
	padding-bottom: 1.5rem !important
}

.p-5 {
	padding: 3rem 3rem !important
}

.pt-5 {
	padding-top: 3rem !important
}

.pr-5 {
	padding-right: 3rem !important
}

.pb-5 {
	padding-bottom: 3rem !important
}

.pl-5 {
	padding-left: 3rem !important
}

.px-5 {
	padding-right: 3rem !important;
	padding-left: 3rem !important
}

.py-5 {
	padding-top: 3rem !important;
	padding-bottom: 3rem !important
}

.m-auto {
	margin: auto !important
}

.mt-auto {
	margin-top: auto !important
}

.mr-auto {
	margin-right: auto !important
}

.mb-auto {
	margin-bottom: auto !important
}

.ml-auto {
	margin-left: auto !important
}

.mx-auto {
	margin-right: auto !important;
	margin-left: auto !important
}

.my-auto {
	margin-top: auto !important;
	margin-bottom: auto !important
}

@media ( min-width :576px) {
	.m-sm-0 {
		margin: 0 0 !important
	}
	.mt-sm-0 {
		margin-top: 0 !important
	}
	.mr-sm-0 {
		margin-right: 0 !important
	}
	.mb-sm-0 {
		margin-bottom: 0 !important
	}
	.ml-sm-0 {
		margin-left: 0 !important
	}
	.mx-sm-0 {
		margin-right: 0 !important;
		margin-left: 0 !important
	}
	.my-sm-0 {
		margin-top: 0 !important;
		margin-bottom: 0 !important
	}
	.m-sm-1 {
		margin: .25rem .25rem !important
	}
	.mt-sm-1 {
		margin-top: .25rem !important
	}
	.mr-sm-1 {
		margin-right: .25rem !important
	}
	.mb-sm-1 {
		margin-bottom: .25rem !important
	}
	.ml-sm-1 {
		margin-left: .25rem !important
	}
	.mx-sm-1 {
		margin-right: .25rem !important;
		margin-left: .25rem !important
	}
	.my-sm-1 {
		margin-top: .25rem !important;
		margin-bottom: .25rem !important
	}
	.m-sm-2 {
		margin: .5rem .5rem !important
	}
	.mt-sm-2 {
		margin-top: .5rem !important
	}
	.mr-sm-2 {
		margin-right: .5rem !important
	}
	.mb-sm-2 {
		margin-bottom: .5rem !important
	}
	.ml-sm-2 {
		margin-left: .5rem !important
	}
	.mx-sm-2 {
		margin-right: .5rem !important;
		margin-left: .5rem !important
	}
	.my-sm-2 {
		margin-top: .5rem !important;
		margin-bottom: .5rem !important
	}
	.m-sm-3 {
		margin: 1rem 1rem !important
	}
	.mt-sm-3 {
		margin-top: 1rem !important
	}
	.mr-sm-3 {
		margin-right: 1rem !important
	}
	.mb-sm-3 {
		margin-bottom: 1rem !important
	}
	.ml-sm-3 {
		margin-left: 1rem !important
	}
	.mx-sm-3 {
		margin-right: 1rem !important;
		margin-left: 1rem !important
	}
	.my-sm-3 {
		margin-top: 1rem !important;
		margin-bottom: 1rem !important
	}
	.m-sm-4 {
		margin: 1.5rem 1.5rem !important
	}
	.mt-sm-4 {
		margin-top: 1.5rem !important
	}
	.mr-sm-4 {
		margin-right: 1.5rem !important
	}
	.mb-sm-4 {
		margin-bottom: 1.5rem !important
	}
	.ml-sm-4 {
		margin-left: 1.5rem !important
	}
	.mx-sm-4 {
		margin-right: 1.5rem !important;
		margin-left: 1.5rem !important
	}
	.my-sm-4 {
		margin-top: 1.5rem !important;
		margin-bottom: 1.5rem !important
	}
	.m-sm-5 {
		margin: 3rem 3rem !important
	}
	.mt-sm-5 {
		margin-top: 3rem !important
	}
	.mr-sm-5 {
		margin-right: 3rem !important
	}
	.mb-sm-5 {
		margin-bottom: 3rem !important
	}
	.ml-sm-5 {
		margin-left: 3rem !important
	}
	.mx-sm-5 {
		margin-right: 3rem !important;
		margin-left: 3rem !important
	}
	.my-sm-5 {
		margin-top: 3rem !important;
		margin-bottom: 3rem !important
	}
	.p-sm-0 {
		padding: 0 0 !important
	}
	.pt-sm-0 {
		padding-top: 0 !important
	}
	.pr-sm-0 {
		padding-right: 0 !important
	}
	.pb-sm-0 {
		padding-bottom: 0 !important
	}
	.pl-sm-0 {
		padding-left: 0 !important
	}
	.px-sm-0 {
		padding-right: 0 !important;
		padding-left: 0 !important
	}
	.py-sm-0 {
		padding-top: 0 !important;
		padding-bottom: 0 !important
	}
	.p-sm-1 {
		padding: .25rem .25rem !important
	}
	.pt-sm-1 {
		padding-top: .25rem !important
	}
	.pr-sm-1 {
		padding-right: .25rem !important
	}
	.pb-sm-1 {
		padding-bottom: .25rem !important
	}
	.pl-sm-1 {
		padding-left: .25rem !important
	}
	.px-sm-1 {
		padding-right: .25rem !important;
		padding-left: .25rem !important
	}
	.py-sm-1 {
		padding-top: .25rem !important;
		padding-bottom: .25rem !important
	}
	.p-sm-2 {
		padding: .5rem .5rem !important
	}
	.pt-sm-2 {
		padding-top: .5rem !important
	}
	.pr-sm-2 {
		padding-right: .5rem !important
	}
	.pb-sm-2 {
		padding-bottom: .5rem !important
	}
	.pl-sm-2 {
		padding-left: .5rem !important
	}
	.px-sm-2 {
		padding-right: .5rem !important;
		padding-left: .5rem !important
	}
	.py-sm-2 {
		padding-top: .5rem !important;
		padding-bottom: .5rem !important
	}
	.p-sm-3 {
		padding: 1rem 1rem !important
	}
	.pt-sm-3 {
		padding-top: 1rem !important
	}
	.pr-sm-3 {
		padding-right: 1rem !important
	}
	.pb-sm-3 {
		padding-bottom: 1rem !important
	}
	.pl-sm-3 {
		padding-left: 1rem !important
	}
	.px-sm-3 {
		padding-right: 1rem !important;
		padding-left: 1rem !important
	}
	.py-sm-3 {
		padding-top: 1rem !important;
		padding-bottom: 1rem !important
	}
	.p-sm-4 {
		padding: 1.5rem 1.5rem !important
	}
	.pt-sm-4 {
		padding-top: 1.5rem !important
	}
	.pr-sm-4 {
		padding-right: 1.5rem !important
	}
	.pb-sm-4 {
		padding-bottom: 1.5rem !important
	}
	.pl-sm-4 {
		padding-left: 1.5rem !important
	}
	.px-sm-4 {
		padding-right: 1.5rem !important;
		padding-left: 1.5rem !important
	}
	.py-sm-4 {
		padding-top: 1.5rem !important;
		padding-bottom: 1.5rem !important
	}
	.p-sm-5 {
		padding: 3rem 3rem !important
	}
	.pt-sm-5 {
		padding-top: 3rem !important
	}
	.pr-sm-5 {
		padding-right: 3rem !important
	}
	.pb-sm-5 {
		padding-bottom: 3rem !important
	}
	.pl-sm-5 {
		padding-left: 3rem !important
	}
	.px-sm-5 {
		padding-right: 3rem !important;
		padding-left: 3rem !important
	}
	.py-sm-5 {
		padding-top: 3rem !important;
		padding-bottom: 3rem !important
	}
	.m-sm-auto {
		margin: auto !important
	}
	.mt-sm-auto {
		margin-top: auto !important
	}
	.mr-sm-auto {
		margin-right: auto !important
	}
	.mb-sm-auto {
		margin-bottom: auto !important
	}
	.ml-sm-auto {
		margin-left: auto !important
	}
	.mx-sm-auto {
		margin-right: auto !important;
		margin-left: auto !important
	}
	.my-sm-auto {
		margin-top: auto !important;
		margin-bottom: auto !important
	}
}

@media ( min-width :768px) {
	.m-md-0 {
		margin: 0 0 !important
	}
	.mt-md-0 {
		margin-top: 0 !important
	}
	.mr-md-0 {
		margin-right: 0 !important
	}
	.mb-md-0 {
		margin-bottom: 0 !important
	}
	.ml-md-0 {
		margin-left: 0 !important
	}
	.mx-md-0 {
		margin-right: 0 !important;
		margin-left: 0 !important
	}
	.my-md-0 {
		margin-top: 0 !important;
		margin-bottom: 0 !important
	}
	.m-md-1 {
		margin: .25rem .25rem !important
	}
	.mt-md-1 {
		margin-top: .25rem !important
	}
	.mr-md-1 {
		margin-right: .25rem !important
	}
	.mb-md-1 {
		margin-bottom: .25rem !important
	}
	.ml-md-1 {
		margin-left: .25rem !important
	}
	.mx-md-1 {
		margin-right: .25rem !important;
		margin-left: .25rem !important
	}
	.my-md-1 {
		margin-top: .25rem !important;
		margin-bottom: .25rem !important
	}
	.m-md-2 {
		margin: .5rem .5rem !important
	}
	.mt-md-2 {
		margin-top: .5rem !important
	}
	.mr-md-2 {
		margin-right: .5rem !important
	}
	.mb-md-2 {
		margin-bottom: .5rem !important
	}
	.ml-md-2 {
		margin-left: .5rem !important
	}
	.mx-md-2 {
		margin-right: .5rem !important;
		margin-left: .5rem !important
	}
	.my-md-2 {
		margin-top: .5rem !important;
		margin-bottom: .5rem !important
	}
	.m-md-3 {
		margin: 1rem 1rem !important
	}
	.mt-md-3 {
		margin-top: 1rem !important
	}
	.mr-md-3 {
		margin-right: 1rem !important
	}
	.mb-md-3 {
		margin-bottom: 1rem !important
	}
	.ml-md-3 {
		margin-left: 1rem !important
	}
	.mx-md-3 {
		margin-right: 1rem !important;
		margin-left: 1rem !important
	}
	.my-md-3 {
		margin-top: 1rem !important;
		margin-bottom: 1rem !important
	}
	.m-md-4 {
		margin: 1.5rem 1.5rem !important
	}
	.mt-md-4 {
		margin-top: 1.5rem !important
	}
	.mr-md-4 {
		margin-right: 1.5rem !important
	}
	.mb-md-4 {
		margin-bottom: 1.5rem !important
	}
	.ml-md-4 {
		margin-left: 1.5rem !important
	}
	.mx-md-4 {
		margin-right: 1.5rem !important;
		margin-left: 1.5rem !important
	}
	.my-md-4 {
		margin-top: 1.5rem !important;
		margin-bottom: 1.5rem !important
	}
	.m-md-5 {
		margin: 3rem 3rem !important
	}
	.mt-md-5 {
		margin-top: 3rem !important
	}
	.mr-md-5 {
		margin-right: 3rem !important
	}
	.mb-md-5 {
		margin-bottom: 3rem !important
	}
	.ml-md-5 {
		margin-left: 3rem !important
	}
	.mx-md-5 {
		margin-right: 3rem !important;
		margin-left: 3rem !important
	}
	.my-md-5 {
		margin-top: 3rem !important;
		margin-bottom: 3rem !important
	}
	.p-md-0 {
		padding: 0 0 !important
	}
	.pt-md-0 {
		padding-top: 0 !important
	}
	.pr-md-0 {
		padding-right: 0 !important
	}
	.pb-md-0 {
		padding-bottom: 0 !important
	}
	.pl-md-0 {
		padding-left: 0 !important
	}
	.px-md-0 {
		padding-right: 0 !important;
		padding-left: 0 !important
	}
	.py-md-0 {
		padding-top: 0 !important;
		padding-bottom: 0 !important
	}
	.p-md-1 {
		padding: .25rem .25rem !important
	}
	.pt-md-1 {
		padding-top: .25rem !important
	}
	.pr-md-1 {
		padding-right: .25rem !important
	}
	.pb-md-1 {
		padding-bottom: .25rem !important
	}
	.pl-md-1 {
		padding-left: .25rem !important
	}
	.px-md-1 {
		padding-right: .25rem !important;
		padding-left: .25rem !important
	}
	.py-md-1 {
		padding-top: .25rem !important;
		padding-bottom: .25rem !important
	}
	.p-md-2 {
		padding: .5rem .5rem !important
	}
	.pt-md-2 {
		padding-top: .5rem !important
	}
	.pr-md-2 {
		padding-right: .5rem !important
	}
	.pb-md-2 {
		padding-bottom: .5rem !important
	}
	.pl-md-2 {
		padding-left: .5rem !important
	}
	.px-md-2 {
		padding-right: .5rem !important;
		padding-left: .5rem !important
	}
	.py-md-2 {
		padding-top: .5rem !important;
		padding-bottom: .5rem !important
	}
	.p-md-3 {
		padding: 1rem 1rem !important
	}
	.pt-md-3 {
		padding-top: 1rem !important
	}
	.pr-md-3 {
		padding-right: 1rem !important
	}
	.pb-md-3 {
		padding-bottom: 1rem !important
	}
	.pl-md-3 {
		padding-left: 1rem !important
	}
	.px-md-3 {
		padding-right: 1rem !important;
		padding-left: 1rem !important
	}
	.py-md-3 {
		padding-top: 1rem !important;
		padding-bottom: 1rem !important
	}
	.p-md-4 {
		padding: 1.5rem 1.5rem !important
	}
	.pt-md-4 {
		padding-top: 1.5rem !important
	}
	.pr-md-4 {
		padding-right: 1.5rem !important
	}
	.pb-md-4 {
		padding-bottom: 1.5rem !important
	}
	.pl-md-4 {
		padding-left: 1.5rem !important
	}
	.px-md-4 {
		padding-right: 1.5rem !important;
		padding-left: 1.5rem !important
	}
	.py-md-4 {
		padding-top: 1.5rem !important;
		padding-bottom: 1.5rem !important
	}
	.p-md-5 {
		padding: 3rem 3rem !important
	}
	.pt-md-5 {
		padding-top: 3rem !important
	}
	.pr-md-5 {
		padding-right: 3rem !important
	}
	.pb-md-5 {
		padding-bottom: 3rem !important
	}
	.pl-md-5 {
		padding-left: 3rem !important
	}
	.px-md-5 {
		padding-right: 3rem !important;
		padding-left: 3rem !important
	}
	.py-md-5 {
		padding-top: 3rem !important;
		padding-bottom: 3rem !important
	}
	.m-md-auto {
		margin: auto !important
	}
	.mt-md-auto {
		margin-top: auto !important
	}
	.mr-md-auto {
		margin-right: auto !important
	}
	.mb-md-auto {
		margin-bottom: auto !important
	}
	.ml-md-auto {
		margin-left: auto !important
	}
	.mx-md-auto {
		margin-right: auto !important;
		margin-left: auto !important
	}
	.my-md-auto {
		margin-top: auto !important;
		margin-bottom: auto !important
	}
}

@media ( min-width :992px) {
	.m-lg-0 {
		margin: 0 0 !important
	}
	.mt-lg-0 {
		margin-top: 0 !important
	}
	.mr-lg-0 {
		margin-right: 0 !important
	}
	.mb-lg-0 {
		margin-bottom: 0 !important
	}
	.ml-lg-0 {
		margin-left: 0 !important
	}
	.mx-lg-0 {
		margin-right: 0 !important;
		margin-left: 0 !important
	}
	.my-lg-0 {
		margin-top: 0 !important;
		margin-bottom: 0 !important
	}
	.m-lg-1 {
		margin: .25rem .25rem !important
	}
	.mt-lg-1 {
		margin-top: .25rem !important
	}
	.mr-lg-1 {
		margin-right: .25rem !important
	}
	.mb-lg-1 {
		margin-bottom: .25rem !important
	}
	.ml-lg-1 {
		margin-left: .25rem !important
	}
	.mx-lg-1 {
		margin-right: .25rem !important;
		margin-left: .25rem !important
	}
	.my-lg-1 {
		margin-top: .25rem !important;
		margin-bottom: .25rem !important
	}
	.m-lg-2 {
		margin: .5rem .5rem !important
	}
	.mt-lg-2 {
		margin-top: .5rem !important
	}
	.mr-lg-2 {
		margin-right: .5rem !important
	}
	.mb-lg-2 {
		margin-bottom: .5rem !important
	}
	.ml-lg-2 {
		margin-left: .5rem !important
	}
	.mx-lg-2 {
		margin-right: .5rem !important;
		margin-left: .5rem !important
	}
	.my-lg-2 {
		margin-top: .5rem !important;
		margin-bottom: .5rem !important
	}
	.m-lg-3 {
		margin: 1rem 1rem !important
	}
	.mt-lg-3 {
		margin-top: 1rem !important
	}
	.mr-lg-3 {
		margin-right: 1rem !important
	}
	.mb-lg-3 {
		margin-bottom: 1rem !important
	}
	.ml-lg-3 {
		margin-left: 1rem !important
	}
	.mx-lg-3 {
		margin-right: 1rem !important;
		margin-left: 1rem !important
	}
	.my-lg-3 {
		margin-top: 1rem !important;
		margin-bottom: 1rem !important
	}
	.m-lg-4 {
		margin: 1.5rem 1.5rem !important
	}
	.mt-lg-4 {
		margin-top: 1.5rem !important
	}
	.mr-lg-4 {
		margin-right: 1.5rem !important
	}
	.mb-lg-4 {
		margin-bottom: 1.5rem !important
	}
	.ml-lg-4 {
		margin-left: 1.5rem !important
	}
	.mx-lg-4 {
		margin-right: 1.5rem !important;
		margin-left: 1.5rem !important
	}
	.my-lg-4 {
		margin-top: 1.5rem !important;
		margin-bottom: 1.5rem !important
	}
	.m-lg-5 {
		margin: 3rem 3rem !important
	}
	.mt-lg-5 {
		margin-top: 3rem !important
	}
	.mr-lg-5 {
		margin-right: 3rem !important
	}
	.mb-lg-5 {
		margin-bottom: 3rem !important
	}
	.ml-lg-5 {
		margin-left: 3rem !important
	}
	.mx-lg-5 {
		margin-right: 3rem !important;
		margin-left: 3rem !important
	}
	.my-lg-5 {
		margin-top: 3rem !important;
		margin-bottom: 3rem !important
	}
	.p-lg-0 {
		padding: 0 0 !important
	}
	.pt-lg-0 {
		padding-top: 0 !important
	}
	.pr-lg-0 {
		padding-right: 0 !important
	}
	.pb-lg-0 {
		padding-bottom: 0 !important
	}
	.pl-lg-0 {
		padding-left: 0 !important
	}
	.px-lg-0 {
		padding-right: 0 !important;
		padding-left: 0 !important
	}
	.py-lg-0 {
		padding-top: 0 !important;
		padding-bottom: 0 !important
	}
	.p-lg-1 {
		padding: .25rem .25rem !important
	}
	.pt-lg-1 {
		padding-top: .25rem !important
	}
	.pr-lg-1 {
		padding-right: .25rem !important
	}
	.pb-lg-1 {
		padding-bottom: .25rem !important
	}
	.pl-lg-1 {
		padding-left: .25rem !important
	}
	.px-lg-1 {
		padding-right: .25rem !important;
		padding-left: .25rem !important
	}
	.py-lg-1 {
		padding-top: .25rem !important;
		padding-bottom: .25rem !important
	}
	.p-lg-2 {
		padding: .5rem .5rem !important
	}
	.pt-lg-2 {
		padding-top: .5rem !important
	}
	.pr-lg-2 {
		padding-right: .5rem !important
	}
	.pb-lg-2 {
		padding-bottom: .5rem !important
	}
	.pl-lg-2 {
		padding-left: .5rem !important
	}
	.px-lg-2 {
		padding-right: .5rem !important;
		padding-left: .5rem !important
	}
	.py-lg-2 {
		padding-top: .5rem !important;
		padding-bottom: .5rem !important
	}
	.p-lg-3 {
		padding: 1rem 1rem !important
	}
	.pt-lg-3 {
		padding-top: 1rem !important
	}
	.pr-lg-3 {
		padding-right: 1rem !important
	}
	.pb-lg-3 {
		padding-bottom: 1rem !important
	}
	.pl-lg-3 {
		padding-left: 1rem !important
	}
	.px-lg-3 {
		padding-right: 1rem !important;
		padding-left: 1rem !important
	}
	.py-lg-3 {
		padding-top: 1rem !important;
		padding-bottom: 1rem !important
	}
	.p-lg-4 {
		padding: 1.5rem 1.5rem !important
	}
	.pt-lg-4 {
		padding-top: 1.5rem !important
	}
	.pr-lg-4 {
		padding-right: 1.5rem !important
	}
	.pb-lg-4 {
		padding-bottom: 1.5rem !important
	}
	.pl-lg-4 {
		padding-left: 1.5rem !important
	}
	.px-lg-4 {
		padding-right: 1.5rem !important;
		padding-left: 1.5rem !important
	}
	.py-lg-4 {
		padding-top: 1.5rem !important;
		padding-bottom: 1.5rem !important
	}
	.p-lg-5 {
		padding: 3rem 3rem !important
	}
	.pt-lg-5 {
		padding-top: 3rem !important
	}
	.pr-lg-5 {
		padding-right: 3rem !important
	}
	.pb-lg-5 {
		padding-bottom: 3rem !important
	}
	.pl-lg-5 {
		padding-left: 3rem !important
	}
	.px-lg-5 {
		padding-right: 3rem !important;
		padding-left: 3rem !important
	}
	.py-lg-5 {
		padding-top: 3rem !important;
		padding-bottom: 3rem !important
	}
	.m-lg-auto {
		margin: auto !important
	}
	.mt-lg-auto {
		margin-top: auto !important
	}
	.mr-lg-auto {
		margin-right: auto !important
	}
	.mb-lg-auto {
		margin-bottom: auto !important
	}
	.ml-lg-auto {
		margin-left: auto !important
	}
	.mx-lg-auto {
		margin-right: auto !important;
		margin-left: auto !important
	}
	.my-lg-auto {
		margin-top: auto !important;
		margin-bottom: auto !important
	}
}

@media ( min-width :1200px) {
	.m-xl-0 {
		margin: 0 0 !important
	}
	.mt-xl-0 {
		margin-top: 0 !important
	}
	.mr-xl-0 {
		margin-right: 0 !important
	}
	.mb-xl-0 {
		margin-bottom: 0 !important
	}
	.ml-xl-0 {
		margin-left: 0 !important
	}
	.mx-xl-0 {
		margin-right: 0 !important;
		margin-left: 0 !important
	}
	.my-xl-0 {
		margin-top: 0 !important;
		margin-bottom: 0 !important
	}
	.m-xl-1 {
		margin: .25rem .25rem !important
	}
	.mt-xl-1 {
		margin-top: .25rem !important
	}
	.mr-xl-1 {
		margin-right: .25rem !important
	}
	.mb-xl-1 {
		margin-bottom: .25rem !important
	}
	.ml-xl-1 {
		margin-left: .25rem !important
	}
	.mx-xl-1 {
		margin-right: .25rem !important;
		margin-left: .25rem !important
	}
	.my-xl-1 {
		margin-top: .25rem !important;
		margin-bottom: .25rem !important
	}
	.m-xl-2 {
		margin: .5rem .5rem !important
	}
	.mt-xl-2 {
		margin-top: .5rem !important
	}
	.mr-xl-2 {
		margin-right: .5rem !important
	}
	.mb-xl-2 {
		margin-bottom: .5rem !important
	}
	.ml-xl-2 {
		margin-left: .5rem !important
	}
	.mx-xl-2 {
		margin-right: .5rem !important;
		margin-left: .5rem !important
	}
	.my-xl-2 {
		margin-top: .5rem !important;
		margin-bottom: .5rem !important
	}
	.m-xl-3 {
		margin: 1rem 1rem !important
	}
	.mt-xl-3 {
		margin-top: 1rem !important
	}
	.mr-xl-3 {
		margin-right: 1rem !important
	}
	.mb-xl-3 {
		margin-bottom: 1rem !important
	}
	.ml-xl-3 {
		margin-left: 1rem !important
	}
	.mx-xl-3 {
		margin-right: 1rem !important;
		margin-left: 1rem !important
	}
	.my-xl-3 {
		margin-top: 1rem !important;
		margin-bottom: 1rem !important
	}
	.m-xl-4 {
		margin: 1.5rem 1.5rem !important
	}
	.mt-xl-4 {
		margin-top: 1.5rem !important
	}
	.mr-xl-4 {
		margin-right: 1.5rem !important
	}
	.mb-xl-4 {
		margin-bottom: 1.5rem !important
	}
	.ml-xl-4 {
		margin-left: 1.5rem !important
	}
	.mx-xl-4 {
		margin-right: 1.5rem !important;
		margin-left: 1.5rem !important
	}
	.my-xl-4 {
		margin-top: 1.5rem !important;
		margin-bottom: 1.5rem !important
	}
	.m-xl-5 {
		margin: 3rem 3rem !important
	}
	.mt-xl-5 {
		margin-top: 3rem !important
	}
	.mr-xl-5 {
		margin-right: 3rem !important
	}
	.mb-xl-5 {
		margin-bottom: 3rem !important
	}
	.ml-xl-5 {
		margin-left: 3rem !important
	}
	.mx-xl-5 {
		margin-right: 3rem !important;
		margin-left: 3rem !important
	}
	.my-xl-5 {
		margin-top: 3rem !important;
		margin-bottom: 3rem !important
	}
	.p-xl-0 {
		padding: 0 0 !important
	}
	.pt-xl-0 {
		padding-top: 0 !important
	}
	.pr-xl-0 {
		padding-right: 0 !important
	}
	.pb-xl-0 {
		padding-bottom: 0 !important
	}
	.pl-xl-0 {
		padding-left: 0 !important
	}
	.px-xl-0 {
		padding-right: 0 !important;
		padding-left: 0 !important
	}
	.py-xl-0 {
		padding-top: 0 !important;
		padding-bottom: 0 !important
	}
	.p-xl-1 {
		padding: .25rem .25rem !important
	}
	.pt-xl-1 {
		padding-top: .25rem !important
	}
	.pr-xl-1 {
		padding-right: .25rem !important
	}
	.pb-xl-1 {
		padding-bottom: .25rem !important
	}
	.pl-xl-1 {
		padding-left: .25rem !important
	}
	.px-xl-1 {
		padding-right: .25rem !important;
		padding-left: .25rem !important
	}
	.py-xl-1 {
		padding-top: .25rem !important;
		padding-bottom: .25rem !important
	}
	.p-xl-2 {
		padding: .5rem .5rem !important
	}
	.pt-xl-2 {
		padding-top: .5rem !important
	}
	.pr-xl-2 {
		padding-right: .5rem !important
	}
	.pb-xl-2 {
		padding-bottom: .5rem !important
	}
	.pl-xl-2 {
		padding-left: .5rem !important
	}
	.px-xl-2 {
		padding-right: .5rem !important;
		padding-left: .5rem !important
	}
	.py-xl-2 {
		padding-top: .5rem !important;
		padding-bottom: .5rem !important
	}
	.p-xl-3 {
		padding: 1rem 1rem !important
	}
	.pt-xl-3 {
		padding-top: 1rem !important
	}
	.pr-xl-3 {
		padding-right: 1rem !important
	}
	.pb-xl-3 {
		padding-bottom: 1rem !important
	}
	.pl-xl-3 {
		padding-left: 1rem !important
	}
	.px-xl-3 {
		padding-right: 1rem !important;
		padding-left: 1rem !important
	}
	.py-xl-3 {
		padding-top: 1rem !important;
		padding-bottom: 1rem !important
	}
	.p-xl-4 {
		padding: 1.5rem 1.5rem !important
	}
	.pt-xl-4 {
		padding-top: 1.5rem !important
	}
	.pr-xl-4 {
		padding-right: 1.5rem !important
	}
	.pb-xl-4 {
		padding-bottom: 1.5rem !important
	}
	.pl-xl-4 {
		padding-left: 1.5rem !important
	}
	.px-xl-4 {
		padding-right: 1.5rem !important;
		padding-left: 1.5rem !important
	}
	.py-xl-4 {
		padding-top: 1.5rem !important;
		padding-bottom: 1.5rem !important
	}
	.p-xl-5 {
		padding: 3rem 3rem !important
	}
	.pt-xl-5 {
		padding-top: 3rem !important
	}
	.pr-xl-5 {
		padding-right: 3rem !important
	}
	.pb-xl-5 {
		padding-bottom: 3rem !important
	}
	.pl-xl-5 {
		padding-left: 3rem !important
	}
	.px-xl-5 {
		padding-right: 3rem !important;
		padding-left: 3rem !important
	}
	.py-xl-5 {
		padding-top: 3rem !important;
		padding-bottom: 3rem !important
	}
	.m-xl-auto {
		margin: auto !important
	}
	.mt-xl-auto {
		margin-top: auto !important
	}
	.mr-xl-auto {
		margin-right: auto !important
	}
	.mb-xl-auto {
		margin-bottom: auto !important
	}
	.ml-xl-auto {
		margin-left: auto !important
	}
	.mx-xl-auto {
		margin-right: auto !important;
		margin-left: auto !important
	}
	.my-xl-auto {
		margin-top: auto !important;
		margin-bottom: auto !important
	}
}

.text-justify {
	text-align: justify !important
}

.text-nowrap {
	white-space: nowrap !important
}

.text-truncate {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap
}

.text-left {
	text-align: left !important
}

.text-right {
	text-align: right !important
}

.text-center {
	text-align: center !important
}

@media ( min-width :576px) {
	.text-sm-left {
		text-align: left !important
	}
	.text-sm-right {
		text-align: right !important
	}
	.text-sm-center {
		text-align: center !important
	}
}

@media ( min-width :768px) {
	.text-md-left {
		text-align: left !important
	}
	.text-md-right {
		text-align: right !important
	}
	.text-md-center {
		text-align: center !important
	}
}

@media ( min-width :992px) {
	.text-lg-left {
		text-align: left !important
	}
	.text-lg-right {
		text-align: right !important
	}
	.text-lg-center {
		text-align: center !important
	}
}

@media ( min-width :1200px) {
	.text-xl-left {
		text-align: left !important
	}
	.text-xl-right {
		text-align: right !important
	}
	.text-xl-center {
		text-align: center !important
	}
}

.text-lowercase {
	text-transform: lowercase !important
}

.text-uppercase {
	text-transform: uppercase !important
}

.text-capitalize {
	text-transform: capitalize !important
}

.font-weight-normal {
	font-weight: 400
}

.font-weight-bold {
	font-weight: 700
}

.font-italic {
	font-style: italic
}

.text-white {
	color: #fff !important
}

.text-muted {
	color: #636c72 !important
}

a.text-muted:focus, a.text-muted:hover {
	color: #4b5257 !important
}

.text-primary {
	color: #0275d8 !important
}

a.text-primary:focus, a.text-primary:hover {
	color: #025aa5 !important
}

.text-success {
	color: #5cb85c !important
}

a.text-success:focus, a.text-success:hover {
	color: #449d44 !important
}

.text-info {
	color: #5bc0de !important
}

a.text-info:focus, a.text-info:hover {
	color: #31b0d5 !important
}

.text-warning {
	color: #f0ad4e !important
}

a.text-warning:focus, a.text-warning:hover {
	color: #ec971f !important
}

.text-danger {
	color: #d9534f !important
}

a.text-danger:focus, a.text-danger:hover {
	color: #c9302c !important
}

.text-gray-dark {
	color: #292b2c !important
}

a.text-gray-dark:focus, a.text-gray-dark:hover {
	color: #101112 !important
}

.text-hide {
	font: 0/0 a;
	color: transparent;
	text-shadow: none;
	background-color: transparent;
	border: 0
}

.invisible {
	visibility: hidden !important
}

.hidden-xs-up {
	display: none !important
}

@media ( max-width :575px) {
	.hidden-xs-down {
		display: none !important
	}
}

@media ( min-width :576px) {
	.hidden-sm-up {
		display: none !important
	}
}

@media ( max-width :767px) {
	.hidden-sm-down {
		display: none !important
	}
}

@media ( min-width :768px) {
	.hidden-md-up {
		display: none !important
	}
}

@media ( max-width :991px) {
	.hidden-md-down {
		display: none !important
	}
}

@media ( min-width :992px) {
	.hidden-lg-up {
		display: none !important
	}
}

@media ( max-width :1199px) {
	.hidden-lg-down {
		display: none !important
	}
}

@media ( min-width :1200px) {
	.hidden-xl-up {
		display: none !important
	}
}

.hidden-xl-down {
	display: none !important
}

.visible-print-block {
	display: none !important
}

@media print {
	.visible-print-block {
		display: block !important
	}
}

.visible-print-inline {
	display: none !important
}

@media print {
	.visible-print-inline {
		display: inline !important
	}
}

.visible-print-inline-block {
	display: none !important
}

@media print {
	.visible-print-inline-block {
		display: inline-block !important
	}
}

@media print {
	.hidden-print {
		display: none !important
	}
} 
</style>