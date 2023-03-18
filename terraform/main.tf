resource "aws_vpc" "flavor_vpc_1" {
  cidr_block           = "10.0.0.0/16"
  enable_dns_hostnames = true
  enable_dns_support   = true

  tags = {
    Name = "flavor_api_vpc_1"
  }
}

resource "aws_subnet" "flavor_subnet_1" {
  vpc_id                  = aws_vpc.flavor_vpc_1.id
  cidr_block              = "10.0.1.0/24"
  availability_zone       = "us-east-1a"
  map_public_ip_on_launch = true

  tags = {
    Name = "flavor_api_subnet"
  }
}

resource "aws_internet_gateway" "flavor_gateway" {
  vpc_id = aws_vpc.flavor_vpc_1.id

  tags = {
    Name = "flavorapi_gateway"
  }
}

resource "aws_route_table" "flavor_route_table" {
  vpc_id = aws_vpc.flavor_vpc_1.id

  tags = {
    Name = "flavorapi_route_table"
  }
}

resource "aws_route" "flavor_route" {
  route_table_id         = aws_route_table.flavor_route_table.id
  destination_cidr_block = "0.0.0.0/0"
  gateway_id             = aws_internet_gateway.flavor_gateway.id
}

resource "aws_route_table_association" "flavor_rtb_ass" {
  route_table_id = aws_route_table.flavor_route_table.id
  subnet_id      = aws_subnet.flavor_subnet_1.id
}

resource "aws_instance" "flavor_ec2" {
  instance_type          = "t2.micro"
  key_name               = aws_key_pair.flavor_key.id
  vpc_security_group_ids = [aws_security_group.flavor_sg.id]
  subnet_id              = aws_subnet.flavor_subnet_1.id
  ami                    = data.aws_ami.flavor_ami.id

  root_block_device {
    volume_size = 8
  }

  tags = {
    Name = "flavorapi_ec2"
  }
}