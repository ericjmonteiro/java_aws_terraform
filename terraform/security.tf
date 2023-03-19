resource "aws_security_group" "flavor_sg" {
  name        = "flavorapi_sg"
  description = "flavor api security group"
  vpc_id      = aws_vpc.flavor_vpc_1.id

  tags = {
    "Name" = "flavorapi_sg"
  }
}

resource "aws_security_group_rule" "sg_public_out" {
  from_port         = 0
  protocol          = "-1"
  security_group_id = aws_security_group.flavor_sg.id
  to_port           = 0
  type              = "egress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_security_group_rule" "sg_ssh_in" {
  from_port         = 22
  protocol          = "tcp"
  security_group_id = aws_security_group.flavor_sg.id
  to_port           = 22
  type              = "ingress"
  cidr_blocks       = ["0.0.0.0/0"]
}

resource "aws_key_pair" "flavor_key" {
  public_key = file("~/.ssh/flavor_api.pub")
  key_name   = "flavor_api"
}

resource "aws_security_group_rule" "public_in_http" {
  type              = "ingress"
  from_port         = 80
  to_port           = 80
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.flavor_sg.id
}

resource "aws_security_group_rule" "public_in_http_83" {
  type              = "ingress"
  from_port         = 80
  to_port           = 8083
  protocol          = "tcp"
  cidr_blocks       = ["0.0.0.0/0"]
  security_group_id = aws_security_group.flavor_sg.id
}